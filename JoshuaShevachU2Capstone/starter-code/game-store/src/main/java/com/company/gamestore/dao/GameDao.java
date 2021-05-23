package com.company.gamestore.dao;

import com.company.gamestore.model.Game;

import java.util.List;

/*
Search for Games by Studio
Search for Games by ESRB Rating
Search for Games by Title
 */
public interface GameDao {
    Game addGame(Game game);

    Game getGame(long id);

    void updateGame(Game console);

    void deleteGame(long id);

    List<Game> getAllGames();

    List<Game> getGamesByStudio(String studio);

    List<Game> getGamesByEsrb(String esrb);

    List<Game> getGamesByTitle(String title);


}
