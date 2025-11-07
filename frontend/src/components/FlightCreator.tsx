import { useState, type FormEvent } from 'react';
import { api } from '../services/api';
import type { FlightResponse } from '../types';

const initialForm = {
  type: 'DOMESTIC',
  code: 'AS101',
  origin: 'MEX',
  destination: 'JFK',
  aircraftModel: 'B737',
  basePrice: 250
};

export function FlightCreator() {
  const [form, setForm] = useState(initialForm);
  const [result, setResult] = useState<FlightResponse | null>(null);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    setLoading(true);
    try {
      const response = await api.createFlight(form);
      setResult(response);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <h2>Factory Method · Flight Creator</h2>
      <form onSubmit={handleSubmit}>
        <select
          value={form.type}
          onChange={(event) => setForm({ ...form, type: event.target.value })}
        >
          <option value="DOMESTIC">Domestic</option>
          <option value="INTERNATIONAL">International</option>
        </select>
        <input
          placeholder="Code"
          value={form.code}
          onChange={(event) => setForm({ ...form, code: event.target.value })}
        />
        <input
          placeholder="Origin"
          value={form.origin}
          onChange={(event) => setForm({ ...form, origin: event.target.value })}
        />
        <input
          placeholder="Destination"
          value={form.destination}
          onChange={(event) => setForm({ ...form, destination: event.target.value })}
        />
        <input
          placeholder="Aircraft Model"
          value={form.aircraftModel}
          onChange={(event) => setForm({ ...form, aircraftModel: event.target.value })}
        />
        <input
          type="number"
          placeholder="Base Price"
          value={form.basePrice}
          onChange={(event) => setForm({ ...form, basePrice: Number(event.target.value) })}
        />
        <button type="submit" disabled={loading}>
          {loading ? 'Creating…' : 'Create Flight'}
        </button>
      </form>
      {result && (
        <pre>{JSON.stringify(result, null, 2)}</pre>
      )}
    </div>
  );
}
