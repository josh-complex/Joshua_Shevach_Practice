package com.company.gamestore.dao;

import com.company.gamestore.model.Game;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoJdbcTemplateImplTest {

    @Autowired
    GameDao gameDao;

    @Before
    public void setUp() throws Exception {
        gameDao.getAllGames().forEach(game -> gameDao.deleteGame(game.getId()));
    }

    @org.junit.Test
    public void shouldAddFindDeleteGame() {

        //Arrange
        Game newGame = new Game();
        newGame.setQuantity(1);
        newGame.setPrice( new BigDecimal("10.05"));
        newGame.setDescription("Fun Game for all");
        newGame.setEsrbRating("R18");
        newGame.setStudio("AE");
        newGame.setTitle("SimCopter");

        //Act
        newGame = gameDao.addGame(newGame);
        Game foundCon = gameDao.getGame(newGame.getId());

        //Assert
        assertEquals(newGame,foundCon);

        //Arrange
        newGame.setQuantity(5);
        newGame.setDescription("Suspense in the air");

        //Act
        gameDao.updateGame(newGame);
        foundCon = gameDao.getGame(newGame.getId());

        //Assert
        assertEquals(newGame,foundCon);

        //Act
        gameDao.deleteGame(newGame.getId());
        foundCon = gameDao.getGame(newGame.getId());

        //Assert
        assertNull(foundCon);
    }

    @org.junit.Test
    public void shouldFindAllGame(){
        //Arrange
        Game newGame1 = new Game();
        newGame1.setQuantity(1);
        newGame1.setPrice( new BigDecimal("10.05"));
        newGame1.setDescription("Fun Game for all");
        newGame1.setEsrbRating("R18");
        newGame1.setStudio("AE");
        newGame1.setTitle("SimCopter");

        Game newGame2 = new Game();
        newGame2.setQuantity(1);
        newGame2.setPrice( new BigDecimal("20.05"));
        newGame2.setDescription("Strategy 4 Everyone");
        newGame2.setEsrbRating("E");
        newGame2.setStudio("YoYo");
        newGame2.setTitle("FortLine");

        //Act
        newGame1 = gameDao.addGame(newGame1);
        newGame2 = gameDao.addGame(newGame2);
        List<Game> allGame = new ArrayList();
        allGame.add(newGame1);
        allGame.add(newGame2);

        //Act
        List<Game> foundAllGame = gameDao.getAllGames();

        //Assert
        assertEquals(allGame.size(),foundAllGame.size());
    }

    @org.junit.Test
    public void shouldFindGameByEsrb(){
        //Arrange
        Game newGame1 = new Game();
        newGame1.setQuantity(1);
        newGame1.setPrice( new BigDecimal("10.05"));
        newGame1.setDescription("Fun Game for all");
        newGame1.setEsrbRating("E");
        newGame1.setStudio("AE");
        newGame1.setTitle("SimCopter");

        Game newGame2 = new Game();
        newGame2.setQuantity(7);
        newGame2.setPrice( new BigDecimal("20.05"));
        newGame2.setDescription("Strategy 4 Everyone");
        newGame2.setEsrbRating("E");
        newGame2.setStudio("YoYo");
        newGame2.setTitle("FortLine");

        Game newGame3 = new Game();
        newGame3.setQuantity(3);
        newGame3.setPrice( new BigDecimal("19.05"));
        newGame3.setDescription("NeverLand");
        newGame3.setEsrbRating("PG");
        newGame3.setStudio("GameMaker");
        newGame3.setTitle("Hook");
        //Act
        newGame1 = gameDao.addGame(newGame1);
        newGame2 = gameDao.addGame(newGame2);
        newGame3 = gameDao.addGame(newGame3);

        //Act
        List<Game> foundNoGame = gameDao.getGamesByEsrb("InvalidValue");

        List<Game> foundOneGame = gameDao.getGamesByEsrb("PG");

        List<Game> foundTwoGame = gameDao.getGamesByEsrb("E");

        //Assert
        assertEquals(foundNoGame.size(),0);
        assertEquals(foundOneGame.size(),1);
        assertEquals(foundTwoGame.size(),2);
    }
/*
List<Game> getGamesByStudio(String studio);

    List<Game> getGamesByEsrb(String esrb);

    List<Game> getGamesByTitle(String title);
 */
    @org.junit.Test
    public void shouldFindGameByStudio(){
        //Arrange
        //Arrange
        Game newGame1 = new Game();
        newGame1.setQuantity(1);
        newGame1.setPrice( new BigDecimal("10.05"));
        newGame1.setDescription("Fun Game for all");
        newGame1.setEsrbRating("E");
        newGame1.setStudio("AE");
        newGame1.setTitle("SimCopter");

        Game newGame2 = new Game();
        newGame2.setQuantity(7);
        newGame2.setPrice( new BigDecimal("20.05"));
        newGame2.setDescription("Strategy 4 Everyone");
        newGame2.setEsrbRating("E");
        newGame2.setStudio("YoYo");
        newGame2.setTitle("FortLine");

        Game newGame3 = new Game();
        newGame3.setQuantity(3);
        newGame3.setPrice( new BigDecimal("19.05"));
        newGame3.setDescription("NeverLand");
        newGame3.setEsrbRating("PG");
        newGame3.setStudio("AE");
        newGame3.setTitle("Hook");

        //Act
        newGame1 = gameDao.addGame(newGame1);
        newGame2 = gameDao.addGame(newGame2);
        newGame3 = gameDao.addGame(newGame3);

        //Act
        List<Game> foundNoGame = gameDao.getGamesByStudio("InvalidValue");

        List<Game> foundOneGame = gameDao.getGamesByStudio("YoYo");

        List<Game> foundTwoGame = gameDao.getGamesByStudio("AE");

        //Assert
        assertEquals(foundNoGame.size(),0);
        assertEquals(foundOneGame.size(),1);
        assertEquals(foundTwoGame.size(),2);
    }

    @org.junit.Test
    public void shouldFindGameByTitle(){
        //Arrange
        //Arrange
        Game newGame1 = new Game();
        newGame1.setQuantity(1);
        newGame1.setPrice( new BigDecimal("10.05"));
        newGame1.setDescription("Fun Game for all");
        newGame1.setEsrbRating("E");
        newGame1.setStudio("AE");
        newGame1.setTitle("SimCopter");

        Game newGame2 = new Game();
        newGame2.setQuantity(7);
        newGame2.setPrice( new BigDecimal("20.05"));
        newGame2.setDescription("Strategy 4 Everyone");
        newGame2.setEsrbRating("E");
        newGame2.setStudio("YoYo");
        newGame2.setTitle("FortLine");

        Game newGame3 = new Game();
        newGame3.setQuantity(3);
        newGame3.setPrice( new BigDecimal("19.05"));
        newGame3.setDescription("NeverLand");
        newGame3.setEsrbRating("PG");
        newGame3.setStudio("AE");
        newGame3.setTitle("Hook");

        //Act
        newGame1 = gameDao.addGame(newGame1);
        newGame2 = gameDao.addGame(newGame2);
        newGame3 = gameDao.addGame(newGame3);

        //Act
        List<Game> foundNoGame = gameDao.getGamesByTitle("InvalidValue");

        List<Game> foundOneGame = gameDao.getGamesByTitle("Hook");

        //Assert
        assertEquals(foundNoGame.size(),0);
        assertEquals(foundOneGame.size(),1);
    }
}