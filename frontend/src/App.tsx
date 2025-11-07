import { FlightCreator } from './components/FlightCreator';
import { AircraftConfigurator } from './components/AircraftConfigurator';
import { RoutePlanner } from './components/RoutePlanner';
import { BookingFlow } from './components/BookingFlow';
import { NotificationDemo } from './components/NotificationDemo';
import { BoardingProcessViewer } from './components/BoardingProcessViewer';

function App() {
  return (
    <div>
      <header style={{ textAlign: 'center', padding: '1.25rem 0' }}>
        <h1>AirSafe Airline Operations</h1>
        <p>Every widget hits a GoF pattern end-to-end.</p>
      </header>
      <div className="app-shell">
        <FlightCreator />
        <AircraftConfigurator />
        <RoutePlanner />
        <BookingFlow />
        <NotificationDemo />
        <BoardingProcessViewer />
      </div>
    </div>
  );
}

export default App;
