import { useState, useEffect } from "react";

function EncounterForm({ encounter: initialEncounter, notify }) {
  const [investigators, setInvestigators] = useState([]);
  const [encounter, setEncounter] = useState(initialEncounter);
  const isAdd = initialEncounter.id === 0;

  useEffect(() => {
    fetch("http://localhost:8080/api/investigator")
      .then((response) => response.json())
      .then((result) => setInvestigators(result))
      .catch(console.log);
  }, []);

  function handleChange(evt) {
    const clone = { ...encounter };

    if (evt.target.name === "investigators") {
      var children = evt.target.children;
      var investigators = [...children]
        .filter((x) => x.selected)
        .map((x) => JSON.parse(x.getAttribute("data-investigator")));
      clone[evt.target.name] = investigators;
    } else clone[evt.target.name] = evt.target.value;

    setEncounter(clone);
  }

  function handleSubmit(evt) {
    evt.preventDefault();

    const url = isAdd
      ? "http://localhost:8080/api/encounter"
      : `http://localhost:8080/api/encounter/${encounter.id}`;
    const method = isAdd ? "POST" : "PUT";
    const expectedStatus = isAdd ? 201 : 204;

    const init = {
      method,
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(encounter),
    };

    fetch(url, init)
      .then((response) => {
        if (response.status === expectedStatus) {
          if (isAdd) {
            return response.json();
          } else {
            return encounter;
          }
        }
        return Promise.reject(
          `Didn't receive expected status: ${expectedStatus}`
        );
      })
      .then((result) =>
        notify({
          action: isAdd ? "add" : "edit",
          encounter: result,
        })
      )
      .catch((error) => notify({ error }));
  }

  return (
    <>
      <h1>{!isAdd ? "Edit" : "Add"} Encounter</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="brief">Brief</label>
          <input
            type="text"
            id="brief"
            name="brief"
            className="form-control"
            value={encounter.brief}
            onChange={handleChange}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="details">Details</label>
          <textarea
            id="details"
            name="details"
            className="form-control"
            value={encounter.details}
            onChange={handleChange}
          ></textarea>
        </div>
        <div className="mb-3">
          <label htmlFor="dateTime">Date &amp; Time</label>
          <input
            type="text"
            id="dateTime"
            name="dateTime"
            className="form-control"
            value={encounter.dateTime}
            onChange={handleChange}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="imageUrl">Image</label>
          <input
            type="url"
            id="imageUrl"
            name="imageUrl"
            className="form-control"
            value={encounter.imageUrl || ""}
            onChange={handleChange}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="investigators">Investigators (<i>Ctrl click to select multiple</i>)</label>
          <div className="row">
            <div className="col">
              <input
                className="investigator-selected-box form-control mb-1 d-inline-flex"
                value={encounter.investigators && encounter.investigators.map(x => " " + x.firstName + " " + x.lastName)}
                disabled
              />
            </div>
            <div className="col">
              <button className="btn btn-primary d-inline" onClick={(event) => { event.preventDefault(); window.location.reload(); }}>
                New Investigator
              </button>
            </div>
          </div>
  
          <select
            multiple
            className="form-select"
            name="investigators"
            id="investigators"
            onChange={(event) => {
              var options = [...event.target.children];
              var selectedOptions = event.target.parentElement.querySelector(
                ".investigator-selected-box"
              );
              selectedOptions.value = "";
              options.forEach((x) => {
                if (selectedOptions.value.length && x.selected)
                  selectedOptions.value += ", ";
                selectedOptions.value += x.selected ? x.value : "";
              });
              handleChange(event);
            }}
          >
            {investigators.map((x) => {
              var investigatorName = x.firstName + " " + x.lastName;
              return (
                <option
                  key={x.id}
                  data-investigator={JSON.stringify(x)}
                  value={investigatorName}
                >
                  {investigatorName}
                </option>
              );
            })}
          </select>
        </div>
        <div className="mb-3">
          <button className="btn btn-primary me-3" type="submit">
            Save
          </button>
          <button
            className="btn btn-secondary"
            type="button"
            onClick={() => notify({ action: "cancel" })}
          >
            Cancel
          </button>
        </div>
      </form>
    </>
  );
}

export default EncounterForm;
