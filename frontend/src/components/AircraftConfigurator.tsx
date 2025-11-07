import { useState, type FormEvent } from 'react';
import { api } from '../services/api';
import type { AircraftAssemblyResponse, AircraftCloneResponse } from '../types';

export function AircraftConfigurator() {
  const [assemblyRequest, setAssemblyRequest] = useState({ manufacturer: 'BOEING', model: '737 MAX' });
  const [assemblyResult, setAssemblyResult] = useState<AircraftAssemblyResponse | null>(null);

  const [cloneRequest, setCloneRequest] = useState({
    baseModel: 'A320',
    variant: 'Neo Plus',
    seatingCapacity: 210,
    cabinConfiguration: 'Economy + Premium'
  });
  const [cloneResult, setCloneResult] = useState<AircraftCloneResponse | null>(null);

  const handleAssemble = async (event: FormEvent) => {
    event.preventDefault();
    const result = await api.assembleAircraft(assemblyRequest);
    setAssemblyResult(result);
  };

  const handleClone = async (event: FormEvent) => {
    event.preventDefault();
    const result = await api.cloneAircraft({
      ...cloneRequest,
      seatingCapacity: Number(cloneRequest.seatingCapacity)
    });
    setCloneResult(result);
  };

  return (
    <div className="card">
      <h2>Abstract Factory · Prototype · Aircraft</h2>
      <form onSubmit={handleAssemble}>
        <strong>Assemble Components</strong>
        <select
          value={assemblyRequest.manufacturer}
          onChange={(event) => setAssemblyRequest({ ...assemblyRequest, manufacturer: event.target.value })}
        >
          <option value="BOEING">Boeing</option>
          <option value="AIRBUS">Airbus</option>
        </select>
        <input
          value={assemblyRequest.model}
          onChange={(event) => setAssemblyRequest({ ...assemblyRequest, model: event.target.value })}
        />
        <button type="submit">Build Aircraft</button>
        {assemblyResult && <pre>{JSON.stringify(assemblyResult, null, 2)}</pre>}
      </form>

      <form onSubmit={handleClone}>
        <strong>Clone Variant</strong>
        <input
          value={cloneRequest.baseModel}
          onChange={(event) => setCloneRequest({ ...cloneRequest, baseModel: event.target.value })}
          placeholder="Base model"
        />
        <input
          value={cloneRequest.variant}
          onChange={(event) => setCloneRequest({ ...cloneRequest, variant: event.target.value })}
          placeholder="Variant name"
        />
        <input
          type="number"
          value={cloneRequest.seatingCapacity}
          onChange={(event) => setCloneRequest({ ...cloneRequest, seatingCapacity: Number(event.target.value) })}
          placeholder="Seats"
        />
        <input
          value={cloneRequest.cabinConfiguration}
          onChange={(event) => setCloneRequest({ ...cloneRequest, cabinConfiguration: event.target.value })}
          placeholder="Cabin"
        />
        <button type="submit">Clone Prototype</button>
        {cloneResult && <pre>{JSON.stringify(cloneResult, null, 2)}</pre>}
      </form>
    </div>
  );
}
