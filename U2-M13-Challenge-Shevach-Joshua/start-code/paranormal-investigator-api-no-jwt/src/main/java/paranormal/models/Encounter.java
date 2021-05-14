package paranormal.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import paranormal.json.LocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Encounter {

    private int id;
    private String brief;
    private String details;
    @JsonFormat(pattern = "dd-MMM-yyyy hh:mma")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTime;
    private String imageUrl;
    private ArrayList<Investigator> investigators = new ArrayList<>();

    public Encounter() {
    }

    public Encounter(int id, String brief, String details, LocalDateTime dateTime, String imageUrl) {
        this.id = id;
        this.brief = brief;
        this.details = details;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Investigator> getInvestigators() {
        return new ArrayList<>(investigators);
    }

    public void setInvestigators(List<Investigator> investigators) {
        this.investigators = new ArrayList<>(investigators);
    }

    public boolean addInvestigator(Investigator investigator) {
        if (investigator == null) {
            return false;
        }
        if (investigators.stream().anyMatch(i -> i.getId() == investigator.getId())) {
            return false;
        }
        return investigators.add(investigator);
    }

    public boolean removeInvestigatorById(int investigatorId) {
        return investigators.removeIf(i -> i.getId() == investigatorId);
    }

}
