package com.company.gamestore.dao;

import com.company.gamestore.model.ProcessingFee;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProcessingFeeDaoJdbcTemplateImpl implements ProcessingFeeDao {

    private final String SELECT_PROCESSING_FEE_INDEX_SQL = "SELECT * FROM processing_fee WHERE product_type = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProcessingFeeDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getProcessingFee(String productType) {
        ProcessingFee tempProcessingFee = this.getProcessingFeeObject(productType);
        if ( tempProcessingFee!=null){
            return tempProcessingFee.getFee();
        }else {
            return  new BigDecimal("0.00");
        }
    }

    @Override
    public ProcessingFee getProcessingFeeObject(String productType) {
        ProcessingFee result;
        try {
            result = jdbcTemplate.queryForObject(SELECT_PROCESSING_FEE_INDEX_SQL,
                    this::mapRowToProcessingFee, productType);
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }
        return result;
    }

    private ProcessingFee mapRowToProcessingFee(ResultSet rs, int rowNumber) throws SQLException {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType(rs.getString("product_type"));
        processingFee.setFee(rs.getBigDecimal("fee"));

        return processingFee;
    }
}
