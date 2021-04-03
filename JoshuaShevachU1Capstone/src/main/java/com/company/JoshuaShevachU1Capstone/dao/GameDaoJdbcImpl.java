package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class GameDaoJdbcImpl implements GameDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_GAME_SQL =
            "insert into game (title, esrb_rating, description, price, studio, quantity) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_GAME_SQL =
            "select * from game where game_id = ?";

    private static final String SELECT_GAMES_BY_STUDIO_SQL =
            "select * from game where studio = ?";

    private static final String SELECT_GAMES_BY_ESRB_RATING_SQL =
            "select * from game where esrb_rating = ?";

    private static final String SELECT_GAMES_BY_TITLE_SQL =
            "select * from game where title = ?";

    private static final String SELECT_ALL_GAMES_SQL =
            "select * from game";

    private static final String UPDATE_GAME_SQL =
            "update game set title = ?, esrb_rating = ?, description = ?, price = ?, studio = ?, quantity = ? where game_id = ?";

    private static final String DELETE_GAME =
            "delete from game where game_id = ?";

    @Autowired
    public GameDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Game addGame(Game game) {
        jdbcTemplate.update(
                INSERT_GAME_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getRemainingInventory());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        game.setId(id);

        return game;
    }

    @Override
    public Game getGame(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_GAME_SQL, this::mapRowToGame, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public List<Game> getAllGames() {
        return jdbcTemplate.query(SELECT_ALL_GAMES_SQL, this::mapRowToGame);
    }

    @Override
    public List<Game> getGamesByStudio(String studio) {
        return jdbcTemplate.query(SELECT_GAMES_BY_STUDIO_SQL, this::mapRowToGame, studio);
    }

    @Override
    public List<Game> getGamesByEsrbRating(String esrbRating) {
        return jdbcTemplate.query(SELECT_GAMES_BY_ESRB_RATING_SQL, this::mapRowToGame, esrbRating);
    }

    @Override
    public List<Game> getGamesByTitle(String title) {
        return jdbcTemplate.query(SELECT_GAMES_BY_TITLE_SQL, this::mapRowToGame, title);
    }

    @Override
    public void updateGame(Game game) {
        jdbcTemplate.update(
                UPDATE_GAME_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getRemainingInventory(),
                game.getId());
    }

    @Override
    public void deleteGame(int id) {
        jdbcTemplate.update(DELETE_GAME, id);
    }

    private Game mapRowToGame(ResultSet rs, int i) throws SQLException {
        return new Game(
                rs.getInt("game_id"),
                rs.getString("title"),
                rs.getString("esrb_rating"),
                rs.getString("description"),
                rs.getBigDecimal("price"),
                rs.getString("studio"),
                rs.getInt("quantity")
        );
    }

}
