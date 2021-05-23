package com.company.gamestore.dao;

import com.company.gamestore.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {

    private final String INSERT_INVOICE_SQL = "INSERT INTO invoice " +
            "(name, street, city, state, zipcode, item_type, item_id, unit_price, quantity, subtotal, tax, processing_fee, total) " +
            "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private final String SELECT_INVOICE_BY_ID_SQL = "SELECT * FROM invoice WHERE invoice_id = ?";

    private final String SELECT_ALL_INVOICES_SQL = "SELECT * FROM invoice";

    private final String SELECT_ALL_INVOICES_SQL_BY_NAME = "SELECT * FROM invoice where name = ?";

    private final String DELETE_INVOICE_BY_ID = "DELETE FROM invoice WHERE invoice_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {

        jdbcTemplate.update(INSERT_INVOICE_SQL,
                invoice.getName(),
                invoice.getStreet(),
                invoice.getCity(),
                invoice.getState(),
                invoice.getZipcode(),
                invoice.getItemType(),
                invoice.getItemId(),
                invoice.getUnitPrice(),
                invoice.getQuantity(),
                invoice.getSubtotal(),
                invoice.getTax(),
                invoice.getProcessingFee(),
                invoice.getTotal());
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        invoice.setId(id);

        return invoice;

    }

    @Override
    public Invoice getInvoice(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_BY_ID_SQL, this::mapRowToInvoice, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {

        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);

    }

    @Override
    public List<Invoice> getAllInvoicesByCustomerName(String name) {

        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL_BY_NAME, this::mapRowToInvoice, name);
    }

    @Override
    public void deleteInvoice(long id) {

        jdbcTemplate.update(DELETE_INVOICE_BY_ID, id);

    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNumber) throws SQLException {

        Invoice invoice = new Invoice();

                invoice.setId(rs.getInt("invoice_id"));
                invoice.setName(rs.getString("name"));
                invoice.setStreet(rs.getString("street"));
                invoice.setCity(rs.getString("city"));
                invoice.setState(rs.getString("state"));
                invoice.setZipcode(rs.getString("zipcode"));
                invoice.setItemType(rs.getString("item_type"));
                invoice.setItemId(rs.getInt("item_id"));
                invoice.setUnitPrice(rs.getBigDecimal("unit_price"));
                invoice.setQuantity(rs.getInt("quantity"));
                invoice.setSubtotal(rs.getBigDecimal("subtotal"));
                invoice.setTax(rs.getBigDecimal("tax"));
                invoice.setProcessingFee(rs.getBigDecimal("processing_fee"));
                invoice.setTotal(rs.getBigDecimal("total"));

                return invoice;
    }
}
