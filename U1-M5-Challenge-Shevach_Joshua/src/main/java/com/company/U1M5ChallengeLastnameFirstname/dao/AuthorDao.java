package com.company.U1M5ChallengeLastnameFirstname.dao;

import com.company.U1M5ChallengeLastnameFirstname.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorDao {

    Author findAuthor(int id);
    Author createAuthor(Author author);
    List<Author> findAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthor(int id);
    
}
