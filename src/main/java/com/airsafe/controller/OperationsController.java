package com.airsafe.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airsafe.aircraft.abstractfactory.AircraftAssembler;
import com.airsafe.aircraft.abstractfactory.AircraftConfiguration;
import com.airsafe.aircraft.abstractfactory.AircraftManufacturer;
import com.airsafe.aircraft.prototype.AircraftRegistry;
import com.airsafe.aircraft.prototype.ConfigurableAircraft;
import com.airsafe.boarding.template.BoardingProcess;
import com.airsafe.boarding.template.BoardingProcessFactory;
import com.airsafe.booking.facade.BookingFacade;
import com.airsafe.dto.AircraftAssemblyRequest;
import com.airsafe.dto.AircraftAssemblyResponse;
import com.airsafe.dto.AircraftCloneRequest;
import com.airsafe.dto.AircraftCloneResponse;
import com.airsafe.dto.BoardingRequest;
import com.airsafe.dto.BoardingResponse;
import com.airsafe.dto.BookingRequest;
import com.airsafe.dto.BookingResponse;
import com.airsafe.dto.FlightCreationRequest;
import com.airsafe.dto.FlightResponse;
import com.airsafe.dto.NotificationRequest;
import com.airsafe.dto.NotificationResponse;
import com.airsafe.dto.RoutePlanRequest;
import com.airsafe.dto.RoutePlanResponse;
import com.airsafe.flight.factory.Flight;
import com.airsafe.flight.factory.FlightFactory;
import com.airsafe.flight.factory.FlightRequest;
import com.airsafe.flight.factory.FlightType;
import com.airsafe.notification.bridge.NotificationBridgeFactory;
import com.airsafe.notification.bridge.Notifier;
import com.airsafe.route.builder.FlightRoute;
import com.airsafe.route.builder.FlightRouteBuilder;
import com.airsafe.route.builder.FlightRouteDirector;
import com.airsafe.route.builder.RouteLeg;

@RestController
@RequestMapping("/api")
public class OperationsController {

    private final FlightFactory flightFactory;
    private final AircraftAssembler aircraftAssembler;
    private final AircraftRegistry aircraftRegistry;
    private final FlightRouteDirector flightRouteDirector;
    private final BookingFacade bookingFacade;
    private final NotificationBridgeFactory notificationBridgeFactory;
    private final BoardingProcessFactory boardingProcessFactory;

    public OperationsController(FlightFactory flightFactory,
                                AircraftAssembler aircraftAssembler,
                                AircraftRegistry aircraftRegistry,
                                FlightRouteDirector flightRouteDirector,
                                BookingFacade bookingFacade,
                                NotificationBridgeFactory notificationBridgeFactory,
                                BoardingProcessFactory boardingProcessFactory) {
        this.flightFactory = flightFactory;
        this.aircraftAssembler = aircraftAssembler;
        this.aircraftRegistry = aircraftRegistry;
        this.flightRouteDirector = flightRouteDirector;
        this.bookingFacade = bookingFacade;
        this.notificationBridgeFactory = notificationBridgeFactory;
        this.boardingProcessFactory = boardingProcessFactory;
    }

    @PostMapping("/flights")
    public FlightResponse createFlight(@RequestBody FlightCreationRequest request) {
        String requestedType = request.type() == null ? "DOMESTIC" : request.type();
        FlightType type = FlightType.valueOf(requestedType.toUpperCase());
        Flight flight = flightFactory.createFlight(new FlightRequest(
                type,
                request.code(),
                request.origin(),
                request.destination(),
                request.aircraftModel(),
                request.basePrice()
        ));
        return new FlightResponse(
                flight.getCode(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getAircraftModel(),
                flight.getBasePrice(),
                flight.policies()
        );
    }

    @PostMapping("/aircraft/assemble")
    public AircraftAssemblyResponse assemble(@RequestBody AircraftAssemblyRequest request) {
        String manufacturerKey = request.manufacturer() == null ? "BOEING" : request.manufacturer();
        AircraftManufacturer manufacturer = AircraftManufacturer.valueOf(manufacturerKey.toUpperCase());
        AircraftConfiguration configuration = aircraftAssembler.assemble(request.model(), manufacturer);
        return new AircraftAssemblyResponse(
                configuration.model(),
                configuration.engine(),
                configuration.cockpit(),
                configuration.seatLayout()
        );
    }

    @PostMapping("/aircraft/clone")
    public AircraftCloneResponse cloneVariant(@RequestBody AircraftCloneRequest request) {
        ConfigurableAircraft aircraft = aircraftRegistry.cloneForVariant(
                request.baseModel(),
                request.variant(),
                request.seatingCapacity(),
                request.cabinConfiguration()
        );
        return new AircraftCloneResponse(
                aircraft.getModel(),
                aircraft.getVariantName(),
                aircraft.getSeatingCapacity(),
                aircraft.getRangeKm(),
                aircraft.getCabinConfiguration()
        );
    }

    @PostMapping("/routes")
    public RoutePlanResponse planRoute(@RequestBody RoutePlanRequest request) {
        FlightRouteBuilder builder = new FlightRouteBuilder();
        FlightRoute route;
        List<RoutePlanRequest.RouteLegInput> inputs = request.legs() == null ? List.of() : request.legs();
        if (request.useDirector() && !inputs.isEmpty()) {
            RoutePlanRequest.RouteLegInput first = inputs.get(0);
            RoutePlanRequest.RouteLegInput last = inputs.get(inputs.size() - 1);
            route = flightRouteDirector.buildExpressRoute(
                    builder,
                    request.flightCode(),
                    first.origin(),
                    last.destination()
            );
        } else {
            builder.reset();
            builder.forFlight(request.flightCode());
            for (RoutePlanRequest.RouteLegInput leg : inputs) {
                builder.addLeg(leg.origin(), leg.destination(), leg.durationMinutes());
            }
            if (request.departureGate() != null) {
                builder.departureGate(request.departureGate());
            }
            if (request.arrivalGate() != null) {
                builder.arrivalGate(request.arrivalGate());
            }
            List<String> fuelStops = request.fuelStops() == null ? List.of() : request.fuelStops();
            fuelStops.forEach(builder::addFuelStop);
            route = builder.build();
        }

        return new RoutePlanResponse(
                route.flightCode(),
                route.legs(),
                route.departureGate(),
                route.arrivalGate(),
                route.fuelStops()
        );
    }

    @PostMapping("/bookings")
    public BookingResponse book(@RequestBody BookingRequest request) {
        return bookingFacade.createBooking(request);
    }

    @PostMapping("/notifications")
    public NotificationResponse notify(@RequestBody NotificationRequest request) {
        Notifier notifier = notificationBridgeFactory.build(request.channel());
        String payload = notifier.notify(request.recipient(), request.message());
        return new NotificationResponse(request.channel(), payload);
    }

    @PostMapping("/boarding")
    public BoardingResponse boarding(@RequestBody BoardingRequest request) {
        String flightType = request.flightType() == null ? "DOMESTIC" : request.flightType();
        BoardingProcess process = boardingProcessFactory.select(flightType);
        return new BoardingResponse(request.flightCode(), process.performBoarding(request.flightCode()));
    }
}
