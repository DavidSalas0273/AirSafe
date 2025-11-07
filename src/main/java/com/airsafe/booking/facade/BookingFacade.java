package com.airsafe.booking.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.airsafe.config.GlobalConfig;
import com.airsafe.dto.BookingRequest;
import com.airsafe.dto.BookingResponse;
import com.airsafe.notification.bridge.NotificationBridgeFactory;
import com.airsafe.notification.bridge.Notifier;
import com.airsafe.service.payment.PaymentService;
import com.airsafe.service.reservation.ReservationService;
import com.airsafe.service.seat.SeatAssignmentService;
import com.airsafe.service.ticket.TicketService;
import com.airsafe.ticket.decorator.Ticket;
import com.airsafe.ticket.decorator.TicketDecoratorFactory;

@Component
public class BookingFacade {

    private final ReservationService reservationService;
    private final SeatAssignmentService seatAssignmentService;
    private final PaymentService paymentService;
    private final TicketService ticketService;
    private final TicketDecoratorFactory ticketDecoratorFactory;
    private final NotificationBridgeFactory notificationBridgeFactory;
    private final GlobalConfig config = GlobalConfig.getInstance();

    public BookingFacade(ReservationService reservationService,
                         SeatAssignmentService seatAssignmentService,
                         PaymentService paymentService,
                         TicketService ticketService,
                         TicketDecoratorFactory ticketDecoratorFactory,
                         NotificationBridgeFactory notificationBridgeFactory) {
        this.reservationService = reservationService;
        this.seatAssignmentService = seatAssignmentService;
        this.paymentService = paymentService;
        this.ticketService = ticketService;
        this.ticketDecoratorFactory = ticketDecoratorFactory;
        this.notificationBridgeFactory = notificationBridgeFactory;
    }

    public BookingResponse createBooking(BookingRequest request) {
        String reservationId = reservationService.reserve(request.passengerName(), request.flightCode());
        String seat = seatAssignmentService.assignSeat(request.seatPreference());
        Ticket baseTicket = ticketService.issue(request.flightCode(), request.basePrice());
        List<String> extras = request.extras() == null ? List.of() : request.extras();
        Ticket decorated = ticketDecoratorFactory.applyExtras(baseTicket, extras);
        double totalCost = decorated.cost();
        String paymentMethod = request.paymentMethod() == null ? "card" : request.paymentMethod();
        String paymentConfirmation = paymentService.charge(paymentMethod, totalCost);

        String channel = request.contactChannel() == null ? "EMAIL" : request.contactChannel();
        Notifier notifier = notificationBridgeFactory.build(channel);
        String contactValue = request.contactValue() == null ? "unknown@airsafe.com" : request.contactValue();
        String notificationPayload = notifier.notify(
                contactValue,
                "%s confirmed for %s".formatted(reservationId, config.getSetting("airlineName"))
        );

        return new BookingResponse(
                reservationId,
                seat,
                paymentConfirmation,
                decorated.description(),
                totalCost,
                notificationPayload
        );
    }
}
