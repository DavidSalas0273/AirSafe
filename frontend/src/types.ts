export type FlightResponse = {
  code: string;
  origin: string;
  destination: string;
  aircraft: string;
  price: number;
  policies: string;
};

export type AircraftAssemblyResponse = {
  model: string;
  engine: string;
  cockpit: string;
  seatLayout: string;
};

export type AircraftCloneResponse = {
  model: string;
  variant: string;
  seatingCapacity: number;
  rangeKm: number;
  cabinConfiguration: string;
};

export type RoutePlanResponse = {
  flightCode: string;
  legs: { origin: string; destination: string; durationMinutes: number }[];
  departureGate: string;
  arrivalGate: string;
  fuelStops: string[];
};

export type BookingResponse = {
  reservationId: string;
  seat: string;
  paymentConfirmation: string;
  ticketDescription: string;
  totalCost: number;
  notificationMessage: string;
};

export type NotificationResponse = {
  channel: string;
  payload: string;
};

export type BoardingResponse = {
  flightCode: string;
  steps: string[];
};
