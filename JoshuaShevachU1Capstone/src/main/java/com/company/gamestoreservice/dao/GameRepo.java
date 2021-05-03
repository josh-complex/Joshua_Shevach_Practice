package com.company.gamestoreservice.dao;

import com.company.gamestoreservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Game, Integer> {

    List<Game> findAllByStudio(String studio);
    List<Game> findAllByEsrbRating(String esrbRating);
    List<Game> findAllByTitleContains(String title);

}
