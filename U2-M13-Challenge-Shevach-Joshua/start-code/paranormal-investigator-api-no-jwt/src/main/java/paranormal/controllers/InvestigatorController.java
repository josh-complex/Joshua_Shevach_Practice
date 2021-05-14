package paranormal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paranormal.domain.EncounterService;
import paranormal.models.Investigator;

import java.util.List;

@RestController
@RequestMapping("/api/investigator")
public class InvestigatorController {

    private final EncounterService service;

    public InvestigatorController(EncounterService service) {
        this.service = service;
    }

    @GetMapping
    public List<Investigator> get() {
        return service.findAllInvestigators();
    }

    @PostMapping
    public ResponseEntity<Investigator> post(@RequestBody Investigator investigator) {
        if (investigator.getId() > 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Investigator result = service.add(investigator);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable int id, @RequestBody Investigator investigator) {
        if (id != investigator.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (service.update(investigator)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.deleteInvestigatorById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}