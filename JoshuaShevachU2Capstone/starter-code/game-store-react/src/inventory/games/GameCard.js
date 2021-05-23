function EncounterCard({ encounter, notify }) {

    function handleDelete() {
        fetch(`http://localhost:8080/api/encounter/${encounter.id}`, { method: "DELETE" })
            .then(() => notify({ action: "delete", encounter }))
            .catch(error => notify({ action: "delete", error }));
    }
  
    return (
        <div className="col mt-4">
            <div className="card" style={{height: '100%'}}>
                <div className="card-header bg-dark text-light fw-bold">
                    {encounter.brief}
                </div>
                {encounter.imageUrl && <img src={encounter.imageUrl} className="card-img-top" alt={encounter.brief} />}
                <div className="card-body">
                    <h5>Timestamp</h5>
                    <p className="card-text">{encounter.dateTime}</p>
                    <h5>Details</h5>
                    <p className="card-text">{encounter.details}</p>
                    <h5>Investigators</h5>
                    <p className="card-text">{encounter.investigators.map(x => x.firstName + " " + x.lastName + ", ")}</p>
                </div>
                <div className="card-footer d-flex bg-dark text-light">
                    <button className="btn btn-danger me-2 w-100" onClick={handleDelete}>DELETE</button>
                    <button className="btn btn-primary w-100" onClick={() => notify({ action: "edit-form", encounter })}>EDIT</button>
                </div>
            </div>
        </div>
    );
}

export default EncounterCard;