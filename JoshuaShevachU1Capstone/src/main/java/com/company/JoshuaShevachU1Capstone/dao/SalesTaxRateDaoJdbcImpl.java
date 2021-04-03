package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SalesTaxRateDaoJdbcImpl implements SalesTaxRateDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_SALES_TAX_RATE_SQL =
            "select * from sales_tax_rate where state = ?";

    @Autowired
    public SalesTaxRateDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalesTaxRate getSalesTaxRateByState(String stateCode) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SALES_TAX_RATE_SQL, this::mapRowToSalesTax, stateCode);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    private SalesTaxRate mapRowToSalesTax(ResultSet resultSet, int i) throws SQLException {
        return new SalesTaxRate(
                resultSet.getString("state"),
                resultSet.getBigDecimal("rate")
        );
    }


}
