package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.ProcessingFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProcessingFeeDaoJdbcImpl implements ProcessingFeeDao{

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_PROCESSING_FEE_SQL =
            "select * from processing_fee where product_type = ?";

    @Autowired
    public ProcessingFeeDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProcessingFee getProcessingFeeByProductType(String productType) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PROCESSING_FEE_SQL, this::mapRowToProcessingFee, productType);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    private ProcessingFee mapRowToProcessingFee(ResultSet resultSet, int i) throws SQLException {
        return new ProcessingFee(
                resultSet.getString("product_type"),
                resultSet.getBigDecimal("fee")
        );
    }

}
