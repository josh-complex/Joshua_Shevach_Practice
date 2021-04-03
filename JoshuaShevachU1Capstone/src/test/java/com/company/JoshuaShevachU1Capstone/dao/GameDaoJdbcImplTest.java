package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.Game;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoJdbcImplTest {

    @Autowired
    GameDao gameDao;

    private Game game;

    @Before
    public void setUp() {
        gameDao.getAllGames()
                .forEach(x -> gameDao.deleteGame(x.getId()));

        game = new Game();
        game.setTitle("Kingdom Hearts");
        game.setEsrbRating("E for everyone");
        game.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        game.setStudio("Square Enix");
        game.setItemType("Games");
        game.setRemainingInventory(1);
        game.setPrice(new BigDecimal("59.99"));
    }

    @Test
    public void shouldAddGetDeleteGame() {
        game = gameDao.addGame(game);

        assertEquals(game, gameDao.getGame(game.getId()));

        gameDao.deleteGame(game.getId());

        assertNull(gameDao.getGame(game.getId()));
    }

    @Test
    public void shouldGetAllGames() {
        gameDao.addGame(game);

        assertEquals(1, gameDao.getAllGames().size());
    }

    @Test
    public void shouldGetGamesByStudio() {
        gameDao.addGame(game);

        List<Game> gamesFromSquare = gameDao.getGamesByStudio("Square Enix");

        assertEquals(1, gamesFromSquare.size());
    }

    @Test
    public void shouldGetGamesByEsrbRating() {
        gameDao.addGame(game);

        List<Game> gamesRatedE = gameDao.getGamesByEsrbRating("E for everyone");

        assertEquals(1, gamesRatedE.size());
    }

    @Test
    public void shouldGetGamesByTitle() {
        gameDao.addGame(game);

        List<Game> games = gameDao.getGamesByTitle("Kingdom Hearts");

        assertEquals(1, games.size());
    }

    @Test
    public void shouldUpdateGame() {
        game = gameDao.addGame(game);

        String expectedDescription = "Have fun with Sora, Donald, and Goofy!";
        game.setDescription(expectedDescription);
        game.setRemainingInventory(358);

        gameDao.updateGame(game);

        game = gameDao.getGame(game.getId());

        assertEquals(expectedDescription, game.getDescription());
        assertEquals(358, game.getRemainingInventory());

    }

}
