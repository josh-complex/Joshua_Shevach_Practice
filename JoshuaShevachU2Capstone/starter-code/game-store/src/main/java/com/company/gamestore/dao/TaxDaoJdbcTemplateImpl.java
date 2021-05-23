package com.company.gamestore.dao;

import com.company.gamestore.model.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class TaxDaoJdbcTemplateImpl implements TaxDao {

    private final String SELECT_TAX_RATE_BY_STATE_SQL = "SELECT * FROM sales_tax_rate WHERE state = UPPER( ? )";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TaxDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tax getTaxRateObject(String state) {
        Tax result;
        try {
            Tax tax = jdbcTemplate.queryForObject(SELECT_TAX_RATE_BY_STATE_SQL, this::mapRowToTax, state);
            result = tax;
        } catch (EmptyResultDataAccessException e) {

            result = null;
        }
        return result;
    }

    @Override
    public BigDecimal getTaxRate(String state) {
        Tax tempTax= this.getTaxRateObject(state);

        if (tempTax != null){
            return tempTax.getRate();
        }else
            return BigDecimal.ZERO;
    }

    private Tax mapRowToTax(ResultSet rs, int rowNumber) throws SQLException {
        Tax tax = new Tax();

        tax.setState(rs.getString("state"));
        tax.setRate(rs.getBigDecimal("rate"));

        return tax;
    }
}
