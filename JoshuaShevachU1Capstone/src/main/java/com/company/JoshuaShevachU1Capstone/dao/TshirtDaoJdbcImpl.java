package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.Tshirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TshirtDaoJdbcImpl implements TshirtDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_TSHIRT_SQL =
            "insert into t_shirt (size, color, description, price, quantity) values (?, ?, ?, ?, ?)";

    private static final String SELECT_TSHIRT_SQL =
            "select * from t_shirt where t_shirt_id = ?";

    private static final String SELECT_TSHIRTS_BY_COLOR_SQL =
            "select * from t_shirt where color = ?";

    private static final String SELECT_TSHIRTS_BY_SIZE_SQL =
            "select * from t_shirt where size = ?";

    private static final String SELECT_ALL_TSHIRTS_SQL =
            "select * from t_shirt";

    private static final String UPDATE_TSHIRT_SQL =
            "update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? where t_shirt_id = ?";

    private static final String DELETE_TSHIRT =
            "delete from t_shirt where t_shirt_id = ?";

    @Autowired
    public TshirtDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Tshirt addTshirt(Tshirt tshirt) {
        jdbcTemplate.update(
                INSERT_TSHIRT_SQL,
                tshirt.getSize(),
                tshirt.getColor(),
                tshirt.getDescription(),
                tshirt.getPrice(),
                tshirt.getRemainingInventory());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        tshirt.setId(id);

        return tshirt;
    }

    @Override
    public Tshirt getTshirt(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TSHIRT_SQL, this::mapRowToTshirt, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public List<Tshirt> getAllTshirts() {
        return jdbcTemplate.query(SELECT_ALL_TSHIRTS_SQL, this::mapRowToTshirt);
    }

    @Override
    public List<Tshirt> getTshirtsByColor(String color) {
        return jdbcTemplate.query(SELECT_TSHIRTS_BY_COLOR_SQL, this::mapRowToTshirt, color);
    }

    @Override
    public List<Tshirt> getTshirtsBySize(String size) {
        return jdbcTemplate.query(SELECT_TSHIRTS_BY_SIZE_SQL, this::mapRowToTshirt, size);
    }

    @Override
    public void updateTshirt(Tshirt tshirt) {
        jdbcTemplate.update(
                UPDATE_TSHIRT_SQL,
                tshirt.getSize(),
                tshirt.getColor(),
                tshirt.getDescription(),
                tshirt.getPrice(),
                tshirt.getRemainingInventory(),
                tshirt.getId());
    }

    @Override
    public void deleteTshirt(int id) {
        jdbcTemplate.update(DELETE_TSHIRT, id);
    }

    private Tshirt mapRowToTshirt(ResultSet rs, int i) throws SQLException {
        return new Tshirt(
                rs.getInt("t_shirt_id"),
                rs.getString("size"),
                rs.getString("color"),
                rs.getString("description"),
                rs.getBigDecimal("price"),
                rs.getInt("quantity")
        );
    }

}
