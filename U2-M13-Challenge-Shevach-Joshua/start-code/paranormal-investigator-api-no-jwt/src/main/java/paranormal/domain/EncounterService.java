package paranormal.domain;

import org.springframework.stereotype.Service;
import paranormal.models.Encounter;
import paranormal.models.Investigator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EncounterService {

    private ArrayList<Investigator> investigators = new ArrayList<>();
    private ArrayList<Encounter> encounters = new ArrayList<>();

    public EncounterService() {

        investigators.add(new Investigator(1, "Koo", "Artaern"));
        investigators.add(new Investigator(2, "\"Tank\"", "Frills"));
        investigators.add(new Investigator(3, "Monto", "Caarew"));
        investigators.add(new Investigator(4, "Eem", "Standard"));

        Encounter e = new Encounter(1, "Tingling sensation",
                "I swear something is near me. I just know it.",
                LocalDateTime.now(),
                null);
        e.addInvestigator(investigators.get(0));
        e.addInvestigator(investigators.get(2));

        encounters.add(e);

        e = new Encounter(2, "Weird noise",
                "Did you hear that?!",
                LocalDateTime.now().minusDays(2),
                "https://upload.wikimedia.org/wikipedia/commons/8/8f/Shunkosai_Hokuei_Obake.jpg");
        e.addInvestigator(investigators.get(1));

        encounters.add(e);

        e = new Encounter(3, "A shape",
                "Look over there. What is that?",
                LocalDateTime.now().minusDays(4),
                "https://upload.wikimedia.org/wikipedia/commons/9/9a/John_A._Russo_as_zombie_in_Night_of_the_Living_Dead.JPG");

        e.addInvestigator(investigators.get(1));
        e.addInvestigator(investigators.get(2));
        e.addInvestigator(investigators.get(3));

        encounters.add(e);
    }

    // Encounters

    public List<Encounter> findAllEncounters() {
        return new ArrayList<>(encounters);
    }

    public Encounter add(Encounter encounter) {

        if (encounter == null) {
            return null;
        }

        setInvestigators(encounter);

        int nextId = encounters.stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;

        encounter.setId(nextId);
        encounters.add(encounter);
        return encounter;
    }

    public boolean update(Encounter encounter) {

        if (encounter == null) {
            return false;
        }

        setInvestigators(encounter);

        for (int i = 0; i < encounters.size(); i++) {
            if (encounters.get(i).getId() == encounter.getId()) {
                encounters.set(i, encounter);
                return true;
            }
        }
        return false;
    }

    public boolean deleteEncounterById(int id) {
        return encounters.removeIf(i -> i.getId() == id);
    }

    // Investigators

    public List<Investigator> findAllInvestigators() {
        return new ArrayList<>(investigators);
    }

    public Investigator add(Investigator investigator) {

        if (investigator == null) {
            return null;
        }

        int nextId = investigators.stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;

        investigator.setId(nextId);
        investigators.add(investigator);
        return investigator;
    }

    public boolean update(Investigator investigator) {

        if (investigator == null) {
            return false;
        }

        for (int i = 0; i < investigators.size(); i++) {
            Investigator memory = investigators.get(i);
            if (memory.getId() == investigator.getId()) {
                // keep reference intact in encounters
                memory.setFirstName(investigator.getFirstName());
                memory.setLastName(investigator.getLastName());
                return true;
            }
        }
        return false;
    }

    public boolean deleteInvestigatorById(int id) {
        for (Encounter encounter : encounters) {
            encounter.removeInvestigatorById(id);
        }
        return investigators.removeIf(i -> i.getId() == id);
    }

    private Encounter setInvestigators(Encounter encounter) {

        List<Investigator> currentInvestigators = encounter.getInvestigators();
        encounter.setInvestigators(new ArrayList<>()); // clear

        if (currentInvestigators != null) {
            for (Investigator investigator : currentInvestigators) {

                Investigator candidate = investigators.stream()
                        .filter(i -> i.getId() == investigator.getId())
                        .findFirst()
                        .orElse(null);

                encounter.addInvestigator(candidate);
            }
        }

        return encounter;
    }
}
