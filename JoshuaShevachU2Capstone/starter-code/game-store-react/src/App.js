import Inventory from "./inventory/Inventory.js";
import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
} from "react-router-dom";

function App() {
  
  return (
    <Router>
      <main className="container" style={{ paddingTop: "65px" }}>
        <nav className="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
          <div className="container align-items-end">
            <a
              className="navbar-brand"
              href="#"
              onClick={() => {
                window.location.reload();
              }}
            >
              <img
                src="https://shekharonline.files.wordpress.com/2011/04/power-button-psd44634.png"
                alt=""
                width="34"
                height="34"
                className="d-inline-block align-text-bottom"
              />
              <h2 className="d-inline">
                {" "}
                <i>
                  Game<span className="text-danger">Store</span>
                </i>
              </h2>
            </a>
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <Link className="nav-link" to="/customers">
                  Customers
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/administrators">
                  Administrators
                </Link>
              </li>
            </ul>
            <form class="d-flex">
              <input
                class="form-control me-2 bg-dark border-primary border-0 border-bottom border-3 rounded-1"
                type="search"
                placeholder="Search"
                aria-label="Search"
                style={{ color: "white" }}
              />
              <button class="btn btn-outline-primary" type="submit">
                Search
              </button>
            </form>
          </div>
        </nav>
        <Switch>
          <Route path="/customers">
            customers
            <Inventory />
          </Route>
          <Route path="/administrators">
            administrators
            <Inventory />
          </Route>
        </Switch>
      </main>
    </Router>
  );
}

export default App;
