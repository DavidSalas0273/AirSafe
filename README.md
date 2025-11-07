# AirSafe Airline Management Demo

Full-stack demo (Spring Boot backend + React/Vite frontend) showcasing real GoF implementations inside an airline operations domain. Each UI widget triggers a backend flow so the patterns stay practical.

## Backend (Java 17 + Spring Boot)

| Pattern | Location | Real usage |
| --- | --- | --- |
| Singleton | `src/main/java/com/airsafe/config/GlobalConfig.java` | Facade reads airline-wide settings (currency, e-ticket prefix) without re-instantiating configuration. |
| Factory Method | `com.airsafe.flight.factory.*` | `FlightFactory` builds domestic vs. international flights with distinct pricing/policies when `/api/flights` is called. |
| Abstract Factory | `com.airsafe.aircraft.abstractfactory.*` | `AircraftAssembler` swaps Boeing/Airbus factories to create engines/cockpits/seat layouts for `/api/aircraft/assemble`. |
| Builder | `com.airsafe.route.builder.*` | `FlightRouteBuilder` incrementally assembles multi-leg routes while `FlightRouteDirector` provides express presets for `/api/routes`. |
| Prototype | `com.airsafe.aircraft.prototype.*` | `AircraftRegistry` clones aircraft blueprints to produce new variants in `/api/aircraft/clone`. |
| Decorator | `com.airsafe.ticket.decorator.*` | Extras like WiFi or luggage wrap a `Ticket` during booking to change cost/description. |
| Facade | `com.airsafe.booking.facade.BookingFacade` | `/api/bookings` hides reservation, seat, payment, ticketing, notification coordination behind one method. |
| Bridge | `com.airsafe.notification.bridge.*` | `Notifier` abstractions pair dynamically with Email/SMS/WhatsApp channels for `/api/bookings` and `/api/notifications`. |
| Template Method | `com.airsafe.boarding.template.*` | `BoardingProcess` fixes boarding skeleton while domestic/international subclasses override checks for `/api/boarding`. |

REST wiring lives in `OperationsController`, which exposes a clear demo flow for each requirement.

## Frontend (TypeScript + React + Vite)

Component-per-pattern dashboard:

- `FlightCreator` → drives the Factory Method endpoint.
- `AircraftConfigurator` → toggles Abstract Factory builds and Prototype cloning.
- `RoutePlanner` → visualizes Builder & Director output.
- `BookingFlow` → exercises Facade + Decorator + Bridge in a single booking.
- `NotificationDemo` → ad-hoc Bridge usage.
- `BoardingProcessViewer` → runs Template Method boarding scripts.

`frontend/src/services/api.ts` centralizes calls to the Spring Boot API (proxy on `/api`).

## Running the stack

1. **Backend**
   ```bash
   mvn spring-boot:run
   ```
   Server listens on `http://localhost:8080`.

2. **Frontend**
   ```bash
   cd frontend
   npm install
   npm run dev
   ```
   Vite serves `http://localhost:5173` and proxies `/api` to the backend. Copy/paste-ready sample payloads are baked into each widget so you can click through every pattern without manual data entry.

Happy flying!
