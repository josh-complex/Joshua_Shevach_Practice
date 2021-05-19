import { useState, useEffect } from "react";
import EncounterCard from "./EncounterCard.js";
import EncounterForm from "./EncounterForm.js";

function Investigators() {
  const [investigators, setInvestigators] = useState([]);
  const [encounters, setEncounters] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [scopedEncounter, setScopedEncounter] = useState({});
  const [error, setError] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/api/investigator")
      .then((response) => response.json())
      .then((result) => setInvestigators(result))
      .catch(console.log);
  }, []);

  function addClick() {
    const now = new Date();
    const year = now.getFullYear();
    const month = new Intl.DateTimeFormat("en-US", { month: "short" }).format(
      now
    );
    const day = new Intl.DateTimeFormat("en-US", { day: "2-digit" }).format(
      now
    );
    const time = new Intl.DateTimeFormat("en-US", {
      hour: "2-digit",
      minute: "2-digit",
    })
      .format(now)
      .replace(" ", "");
    console.log(time);
    setScopedEncounter({
      id: 0,
      brief: "",
      details: "",
      dateTime: `${day}-${month}-${year} ${time}`,
    });
    setShowForm(true);
  }

  function notify({ action, encounter, error }) {
    if (error) {
      setError(error);
      setShowForm(false);
      return;
    }
  
    switch (action) {
      case "add":
        setEncounters([...encounters, encounter]);
        break;
      case "edit":
        setEncounters(
          encounters.map((e) => {
            if (e.id === encounter.id) {
              return encounter;
            }
            return e;
          })
        );
        break;
      case "edit-form":
        setScopedEncounter(encounter);
        setShowForm(true);
        return;
      case "delete":
        setEncounters(encounters.filter((e) => e.id !== encounter.id));
        break;
      default:
        break;
    }
    setShowForm(false);
  }

  if (showForm) {
    return <EncounterForm encounter={scopedEncounter} notify={notify} />;
  }

  return (
    <>
      <div className="container mt-4 p-0">
        <button className="bt btn-lg border-0 btn-primary fw-bold" onClick={addClick}>
          New Encounter
        </button>
      </div>

      {error && <div className="alert alert-danger">{error}</div>}
      {encounters.length === 0 ? (
        <div className="alert alert-warning">No Encounters</div>
      ) : (
        <div className="row row-cols-3">
          {encounters.map((e) => (
            <EncounterCard key={e.id} encounter={e} notify={notify} />
          ))}
        </div>
      )}
    </>
  );
}

export default Investigators;
