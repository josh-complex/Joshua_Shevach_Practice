package com.company.gamestore.dao;

import com.company.gamestore.model.Console;
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
public class ConsoleDaoJdbcTemplateImpl implements ConsoleDao{

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL =
            "INSERT INTO console (model,manufacturer,memory_amount,processor,price,quantity)" +
                    "VALUES (?,?,?,?,?,?)";

    private static final String SELECT_SQL =
            "SELECT * FROM console WHERE console_id = ?";

    private static final String SELECT_ALL_SQL =
            "SELECT * FROM console";

    private static final String SELECT_ALL_BY_MANUFACTURER_SQL =
            "SELECT * FROM console WHERE manufacturer = ?";

    private static final String UPDATE_SQL =
            "UPDATE console SET "+
                    "model=?,"+
                    "manufacturer=?,"+
                    "memory_amount=?,"+
                    "processor=?,"+
                    "price=?,"+
                    "quantity=? "+
                    "WHERE console_id=?";

    private static final String DELETE_SQL =
            "DELETE FROM console WHERE console_id = ?";


    @Autowired
    public ConsoleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate)  {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Console addConsole(Console console) {
        jdbcTemplate.update(
                INSERT_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount().toString(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity());

        long id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);

        console.setId(id);

        return console;
    }

    @Override
    public Console getConsole(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SQL, this::mapRowToModel, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this album id, return null
            return null;
        }
    }

    @Override
    public List<Console> getAllConsoles() {
        return jdbcTemplate.query(SELECT_ALL_SQL, this::mapRowToModel);
    }

    @Override
    public void updateConsole(Console console) {
        jdbcTemplate.update(
                UPDATE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity(),
                console.getId());
    }

    @Override
    public void deleteConsole(long id) {
        jdbcTemplate.update(DELETE_SQL, id);

    }

    @Override
    public List<Console> getConsolesByManufacturer(String manufacturer) {
        return jdbcTemplate.query(SELECT_ALL_BY_MANUFACTURER_SQL,this::mapRowToModel, manufacturer);
    }

    private Console mapRowToModel(ResultSet rs, int rowNum) throws SQLException {
        Console console = new Console();
        console.setId(rs.getInt("console_id"));
        console.setModel(rs.getString("model"));
        console.setManufacturer(rs.getString("manufacturer"));
        console.setMemoryAmount(rs.getString("memory_amount"));
        console.setProcessor(rs.getString("processor"));
        console.setPrice(rs.getBigDecimal("price"));
        console.setQuantity(rs.getLong("quantity"));

        return console;
    }
}
