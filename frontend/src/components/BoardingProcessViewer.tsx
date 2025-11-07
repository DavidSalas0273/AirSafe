import { useState, type FormEvent } from 'react';
import { api } from '../services/api';
import type { BoardingResponse } from '../types';

export function BoardingProcessViewer() {
  const [form, setForm] = useState({ flightType: 'DOMESTIC', flightCode: 'AS305' });
  const [result, setResult] = useState<BoardingResponse | null>(null);

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    const response = await api.boarding(form);
    setResult(response);
  };

  return (
    <div className="card">
      <h2>Template Method · Boarding</h2>
      <form onSubmit={handleSubmit}>
        <select value={form.flightType} onChange={(event) => setForm({ ...form, flightType: event.target.value })}>
          <option value="DOMESTIC">Domestic</option>
          <option value="INTERNATIONAL">International</option>
        </select>
        <input value={form.flightCode} onChange={(event) => setForm({ ...form, flightCode: event.target.value })} />
        <button type="submit">Simulate Boarding</button>
      </form>
      {result && (
        <pre>{JSON.stringify(result, null, 2)}</pre>
      )}
    </div>
  );
}
