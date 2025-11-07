import { FlightCreator } from './components/FlightCreator';
import { AircraftConfigurator } from './components/AircraftConfigurator';
import { RoutePlanner } from './components/RoutePlanner';
import { BookingFlow } from './components/BookingFlow';
import { NotificationDemo } from './components/NotificationDemo';
import { BoardingProcessViewer } from './components/BoardingProcessViewer';

const patternTags = [
  'Singleton',
  'Factory Method',
  'Abstract Factory',
  'Builder',
  'Prototype',
  'Decorator',
  'Facade',
  'Bridge',
  'Template Method'
];

const quickStats = [
  { label: 'Aircraft Families', value: 'Boeing + Airbus' },
  { label: 'Extras', value: 'Priority · WiFi · Luggage' },
  { label: 'Notifiers', value: 'Email · SMS · WhatsApp' }
];

function App() {
  return (
    <div className="page">
      <header className="hero">
        <div>
          <p className="eyebrow">AirSafe Mission Control</p>
          <h1>Airline orchestration sandbox</h1>
          <p>Trigger every Gang of Four pattern inside a realistic ops workflow—from flight creation to the final boarding call.</p>
          <div className="pattern-pills">
            {patternTags.map((tag) => (
              <span key={tag} className="pattern-pill">
                {tag}
              </span>
            ))}
          </div>
        </div>
        <div className="hero-panel">
          <h3>What&apos;s inside this demo?</h3>
          <ul className="stat-list">
            {quickStats.map((stat) => (
              <li key={stat.label} className="stat-item">
                <span>{stat.label}</span>
                <span>{stat.value}</span>
              </li>
            ))}
          </ul>
        </div>
      </header>

      <section className="showcase">
        <h2>Operational playgrounds</h2>
        <p>Open any panel and send a request—each module hits a different backend endpoint to exercise the targeted GoF implementation.</p>
        <div className="app-shell">
          <FlightCreator />
          <AircraftConfigurator />
          <RoutePlanner />
          <BookingFlow />
          <NotificationDemo />
          <BoardingProcessViewer />
        </div>
      </section>
    </div>
  );
}

export default App;
