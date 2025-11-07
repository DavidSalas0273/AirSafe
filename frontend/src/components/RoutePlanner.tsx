import { useState, type FormEvent } from 'react';
import { api } from '../services/api';
import type { RoutePlanResponse } from '../types';

export function RoutePlanner() {
  const [flightCode, setFlightCode] = useState('AS203');
  const [legsInput, setLegsInput] = useState('MEX-HOU-120\nHOU-JFK-180');
  const [useDirector, setUseDirector] = useState(false);
  const [result, setResult] = useState<RoutePlanResponse | null>(null);

  const parseLegs = () =>
    legsInput
      .split('\n')
      .map((row) => row.trim())
      .filter(Boolean)
      .map((row) => {
        const [origin, destination, duration] = row.split('-');
        return { origin, destination, durationMinutes: Number(duration) };
      });

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    const payload = {
      flightCode,
      legs: parseLegs(),
      departureGate: 'A7',
      arrivalGate: 'C2',
      fuelStops: ['MTY'],
      useDirector
    };
    const response = await api.planRoute(payload);
    setResult(response);
  };

  return (
    <div className="card">
      <h2>Builder · Route Planner</h2>
      <form onSubmit={handleSubmit}>
        <input value={flightCode} onChange={(event) => setFlightCode(event.target.value)} />
        <textarea
          rows={4}
          value={legsInput}
          onChange={(event) => setLegsInput(event.target.value)}
          placeholder="Origin-Destination-Duration"
        />
        <label>
          <input type="checkbox" checked={useDirector} onChange={(event) => setUseDirector(event.target.checked)} />
          Use director shortcut
        </label>
        <button type="submit">Build Route</button>
      </form>
      {result && <pre>{JSON.stringify(result, null, 2)}</pre>}
    </div>
  );
}
