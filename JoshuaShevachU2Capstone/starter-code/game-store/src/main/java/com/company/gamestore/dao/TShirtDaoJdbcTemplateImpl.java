package com.company.gamestore.dao;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoJdbcTemplateImpl implements TShirtDao{

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL =
            "INSERT INTO t_shirt " +
            "(size, color, description,price,quantity)" +
            "VALUES (?,?,?,?,?)";

    private static final String SELECT_SQL =
            "SELECT * FROM t_shirt WHERE t_shirt_id = ?";

    private static final String SELECT_ALL_SQL =
            "SELECT * FROM t_shirt";

    private static final String SELECT_ALL_BY_COLOR_SQL =
            "SELECT * FROM t_shirt WHERE color=?";

    private static final String SELECT_ALL_BY_SIZE_SQL =
            "SELECT * FROM t_shirt WHERE size=?";

    private static final String UPDATE_SQL =
            "UPDATE t_shirt SET " +
            "size=?, color=?, description=?,price=?,quantity=? "+
            "where t_shirt_id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM t_shirt WHERE t_shirt_id = ?";

    @Autowired
    public TShirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate)  {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TShirt addTShirt(TShirt tShirt) {
        jdbcTemplate.update(
                INSERT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity());

        long id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);

        tShirt.setId(id);

        return tShirt;
    }

    @Override
    public TShirt getTShirt(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SQL, this::mapRowToModel, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this album id, return null
            return null;
        }
    }

    @Override
    public List<TShirt> getAllTShirts() {
        return jdbcTemplate.query(SELECT_ALL_SQL, this::mapRowToModel);
    }

    @Override
    public List<TShirt> getAllTShirtsByColor(String color) {
        return jdbcTemplate.query(SELECT_ALL_BY_COLOR_SQL, this::mapRowToModel, color);
    }

    @Override
    public List<TShirt> getAllTShirtsBySize(String size) {
        return jdbcTemplate.query(SELECT_ALL_BY_SIZE_SQL, this::mapRowToModel,size);
    }

    @Override
    public void updateTShirt(TShirt tShirt) {
        jdbcTemplate.update(
                UPDATE_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity(),
                tShirt.getId());
    }

    @Override
    public void deleteTShirt(long id) {
        jdbcTemplate.update(DELETE_SQL,id);

    }

    private TShirt mapRowToModel(ResultSet rs, int rowNum) throws SQLException {
        TShirt tShirt = new TShirt();
        tShirt.setId(rs.getInt("t_shirt_id"));
        tShirt.setSize(rs.getString("size"));
        tShirt.setColor(rs.getString("color"));
        tShirt.setDescription(rs.getString("description"));
        tShirt.setPrice(rs.getBigDecimal("price"));
        tShirt.setQuantity(rs.getLong("quantity"));
        return tShirt;
    }
}
