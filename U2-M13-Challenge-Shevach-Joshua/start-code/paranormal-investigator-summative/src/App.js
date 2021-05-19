import Encounters from './Encounters.js';
  
function App() {
  return (
    <main className="container" style={{paddingTop: '65px'}}>
      <nav className="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <div className="container align-items-end">
          <a className="navbar-brand" href="#" onClick={() => { window.location.reload(); }}>
            <img
              src="https://www.speedrun.com/themes/phasmophobia/cover-256.png"
              alt=""
              width="24"
              height="24"
              className="d-inline-block align-baseline"
            />
            <h2 className="d-inline">
              {" "}
              <i>Paranormal Encounters</i>
            </h2>
          </a>
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <a className="nav-link" href="#" onClick={() => { window.location.reload(); }}>Encounters</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Investigators</a>
            </li>
          </ul>
        </div>
      </nav>
      <Encounters />
    </main>
  );
}

export default App;
