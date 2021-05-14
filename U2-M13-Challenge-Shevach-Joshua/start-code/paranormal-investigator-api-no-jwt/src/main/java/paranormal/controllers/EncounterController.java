package paranormal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paranormal.domain.EncounterService;
import paranormal.models.Encounter;

import java.util.List;

@RestController
@RequestMapping("/api/encounter")
@CrossOrigin(origins = "http://localhost:62895")
public class EncounterController {

    private final EncounterService service;

    public EncounterController(EncounterService service) {
        this.service = service;
    }

    @GetMapping
    public List<Encounter> get() {
        return service.findAllEncounters();
    }

    @PostMapping
    public ResponseEntity<Encounter> post(@RequestBody Encounter encounter) {
        if (encounter.getId() > 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Encounter result = service.add(encounter);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable int id, @RequestBody Encounter encounter) {
        if (id != encounter.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (service.update(encounter)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.deleteEncounterById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
