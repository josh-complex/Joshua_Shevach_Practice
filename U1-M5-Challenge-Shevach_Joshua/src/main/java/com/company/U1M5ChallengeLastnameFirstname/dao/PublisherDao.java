package com.company.U1M5ChallengeLastnameFirstname.dao;

import com.company.U1M5ChallengeLastnameFirstname.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PublisherDao {

    Publisher findPublisher(int id);
    Publisher createPublisher(Publisher Publisher);
    List<Publisher> findAllPublishers();
    void updatePublisher(Publisher Publisher);
    void deletePublisher(int id);
}
