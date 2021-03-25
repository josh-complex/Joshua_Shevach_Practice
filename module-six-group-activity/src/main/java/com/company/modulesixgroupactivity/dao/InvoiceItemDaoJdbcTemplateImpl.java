package com.company.modulesixgroupactivity.dao;

import com.company.modulesixgroupactivity.model.Customer;
import com.company.modulesixgroupactivity.model.Invoice;
import com.company.modulesixgroupactivity.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_INVOICE_ITEM_SQL =
            "insert into invoice (customer_id, order_date, pickup_date, return_date, late_fee) values (?, ?, ?, ?, ?)";

    private static final String SELECT_INVOICE_ITEM_BY_CUSTOMER_SQL =
            "select * from invoice where invoice_id = ?";

    private static final String DELETE_INVOICE_ITEM_SQL =
            "delete from invoice where invoice_id = ?";


    @Autowired
    public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public InvoiceItem addInvoiceItem(InvoiceItem invoice) {
        return null;
    }

    @Override
    public InvoiceItem getInvoiceItem(int id) {
        return null;
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return null;
    }

    @Override
    public void deleteInvoiceItem(int id) {

    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setLateFee(rs.getBigDecimal("late_fee"));
        invoice.setOrderDate(rs.getDate("order_date").toLocalDate());
        invoice.setPickupDate(rs.getDate("pickup_date").toLocalDate());
        invoice.setReturnDate(rs.getDate("return_date").toLocalDate());

        return invoice;

    }
}
