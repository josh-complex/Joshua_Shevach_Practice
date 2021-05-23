package com.company.gamestore.dao;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoJdbcTemplateImpl implements GameDao{

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL =
            "INSERT INTO game(" +
                    "title," +
                    "esrb_rating," +
                    "description," +
                    "price," +
                    "studio," +
                    "quantity) " +
                    "VALUES (?,?,?,?,?,?)";


    private static final String SELECT_SQL =
            "SELECT * FROM game WHERE game_id = ?";

    private static final String SELECT_ALL_SQL =
            "SELECT * FROM game";

    private static final String SELECT_ALL_BY_TITLE_SQL =
            "SELECT * FROM game WHERE title =?";

    private static final String SELECT_ALL_BY_ESRB_SQL =
            "SELECT * FROM game WHERE esrb_rating =?";

    private static final String SELECT_ALL_BY_STUDIO_SQL =
            "SELECT * FROM game WHERE studio =?";

    private static final String UPDATE_SQL =
            "UPDATE game SET " +
                    "title=?," +
                    "esrb_rating=?," +
                    "description=?," +
                    "price=?," +
                    "studio=?," +
                    "quantity=? "+
                    "WHERE game_id=?";

    private static final String DELETE_SQL =
            "DELETE FROM game WHERE game_id = ?";


    @Autowired
    public GameDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate)  {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Game addGame(Game game) {
        jdbcTemplate.update(
                INSERT_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity());

        long id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);

        game.setId(id);

        return game;
    }

    @Override
    public Game getGame(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SQL, this::mapRowToModel, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this album id, return null
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        return jdbcTemplate.query(SELECT_ALL_SQL, this::mapRowToModel);
    }

    @Override
    public List<Game> getGamesByStudio(String studio) {
        return jdbcTemplate.query(SELECT_ALL_BY_STUDIO_SQL, this::mapRowToModel, studio);
    }

    @Override
    public List<Game> getGamesByEsrb(String esrb) {
        return jdbcTemplate.query(SELECT_ALL_BY_ESRB_SQL,this::mapRowToModel, esrb);
    }

    @Override
    public List<Game> getGamesByTitle(String title) {
        return jdbcTemplate.query(SELECT_ALL_BY_TITLE_SQL, this::mapRowToModel,title);
    }

    @Override
    public void updateGame(Game game) {
        jdbcTemplate.update(
                UPDATE_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity(),
                game.getId());
    }

    @Override
    public void deleteGame(long id) {
        jdbcTemplate.update(DELETE_SQL, id);

    }

    private Game mapRowToModel(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();
        game.setId(rs.getInt("game_id"));
        game.setTitle(rs.getString("title"));
        game.setEsrbRating(rs.getString("esrb_rating"));
        game.setDescription(rs.getString("description"));
        game.setStudio(rs.getString("studio"));
        game.setPrice(rs.getBigDecimal("price"));
        game.setQuantity(rs.getLong("quantity"));

        return game;
    }
}
