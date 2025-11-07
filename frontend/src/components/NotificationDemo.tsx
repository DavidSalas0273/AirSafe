import { useState, type FormEvent } from 'react';
import { api } from '../services/api';
import type { NotificationResponse } from '../types';

export function NotificationDemo() {
  const [form, setForm] = useState({ channel: 'SMS', recipient: '+521555000', message: 'Gate updated' });
  const [result, setResult] = useState<NotificationResponse | null>(null);

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    const response = await api.notify(form);
    setResult(response);
  };

  return (
    <div className="card">
      <h2>Bridge · Notification</h2>
      <form onSubmit={handleSubmit}>
        <select value={form.channel} onChange={(event) => setForm({ ...form, channel: event.target.value })}>
          <option value="EMAIL">Email</option>
          <option value="SMS">SMS</option>
          <option value="WHATSAPP">WhatsApp</option>
        </select>
        <input value={form.recipient} onChange={(event) => setForm({ ...form, recipient: event.target.value })} />
        <input value={form.message} onChange={(event) => setForm({ ...form, message: event.target.value })} />
        <button type="submit">Send</button>
      </form>
      {result && <pre>{JSON.stringify(result, null, 2)}</pre>}
    </div>
  );
}
