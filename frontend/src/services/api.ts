import type {
  FlightResponse,
  AircraftAssemblyResponse,
  AircraftCloneResponse,
  RoutePlanResponse,
  BookingResponse,
  NotificationResponse,
  BoardingResponse
} from '../types';

async function post<T>(path: string, body: unknown): Promise<T> {
  const response = await fetch(`/api${path}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body)
  });

  if (!response.ok) {
    const text = await response.text();
    throw new Error(text || 'Request failed');
  }
  return response.json() as Promise<T>;
}

export const api = {
  createFlight: (payload: unknown) => post<FlightResponse>('/flights', payload),
  assembleAircraft: (payload: unknown) => post<AircraftAssemblyResponse>('/aircraft/assemble', payload),
  cloneAircraft: (payload: unknown) => post<AircraftCloneResponse>('/aircraft/clone', payload),
  planRoute: (payload: unknown) => post<RoutePlanResponse>('/routes', payload),
  createBooking: (payload: unknown) => post<BookingResponse>('/bookings', payload),
  notify: (payload: unknown) => post<NotificationResponse>('/notifications', payload),
  boarding: (payload: unknown) => post<BoardingResponse>('/boarding', payload)
};
