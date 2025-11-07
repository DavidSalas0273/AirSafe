import { useState, type FormEvent } from 'react';
import { api } from '../services/api';
import type { BookingResponse } from '../types';

const extrasList = [
  { label: 'Priority Boarding', value: 'PRIORITY' },
  { label: 'In-flight WiFi', value: 'WIFI' },
  { label: 'Extra Luggage', value: 'LUGGAGE' }
];

export function BookingFlow() {
  const [form, setForm] = useState({
    passengerName: 'Dana Crew',
    flightCode: 'AS101',
    seatPreference: 'Window',
    basePrice: 320,
    paymentMethod: 'card',
    contactChannel: 'EMAIL',
    contactValue: 'dana@example.com'
  });
  const [selectedExtras, setSelectedExtras] = useState<string[]>(['PRIORITY']);
  const [result, setResult] = useState<BookingResponse | null>(null);

  const toggleExtra = (value: string) => {
    setSelectedExtras((current) =>
      current.includes(value) ? current.filter((item) => item !== value) : [...current, value]
    );
  };

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    const response = await api.createBooking({ ...form, extras: selectedExtras });
    setResult(response);
  };

  return (
    <div className="card">
      <h2>Facade · Decorator · Bridge · Booking</h2>
      <form onSubmit={handleSubmit}>
        <input value={form.passengerName} onChange={(event) => setForm({ ...form, passengerName: event.target.value })} />
        <input value={form.flightCode} onChange={(event) => setForm({ ...form, flightCode: event.target.value })} />
        <input value={form.seatPreference} onChange={(event) => setForm({ ...form, seatPreference: event.target.value })} />
        <input
          type="number"
          value={form.basePrice}
          onChange={(event) => setForm({ ...form, basePrice: Number(event.target.value) })}
        />
        <input value={form.paymentMethod} onChange={(event) => setForm({ ...form, paymentMethod: event.target.value })} />
        <select
          value={form.contactChannel}
          onChange={(event) => setForm({ ...form, contactChannel: event.target.value })}
        >
          <option value="EMAIL">Email</option>
          <option value="SMS">SMS</option>
          <option value="WHATSAPP">WhatsApp</option>
        </select>
        <input
          placeholder="Contact value"
          value={form.contactValue}
          onChange={(event) => setForm({ ...form, contactValue: event.target.value })}
        />
        <div>
          {extrasList.map((extra) => (
            <label key={extra.value}>
              <input
                type="checkbox"
                checked={selectedExtras.includes(extra.value)}
                onChange={() => toggleExtra(extra.value)}
              />
              {extra.label}
            </label>
          ))}
        </div>
        <button type="submit">Book Flight</button>
      </form>
      {result && <pre>{JSON.stringify(result, null, 2)}</pre>}
    </div>
  );
}
