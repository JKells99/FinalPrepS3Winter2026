package com.finalreview.musicstore.invoice;

import com.finalreview.musicstore.database.DBConnection;
import com.finalreview.musicstore.product.Product;

import java.sql.SQLException;
import java.sql.Timestamp;

public class InvoiceDAO {

    public void saveInvoiceToDb(Invoice invoice) {
        String invoiceSql = "INSERT INTO invoices (customer_id, employee_id, total_amount, invoice_date) VALUES (?, ?, ?, ?) RETURNING invoice_id";
        String itemSql = "INSERT INTO invoice_items (invoice_id, product_id) VALUES (?, ?)";

        try (var connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            try (var invoiceStmt = connection.prepareStatement(invoiceSql);
                 var itemStmt = connection.prepareStatement(itemSql)) {

                invoiceStmt.setInt(1, invoice.getCustomer_id());
                invoiceStmt.setInt(2, invoice.getEmployee_id());
                invoiceStmt.setDouble(3, invoice.getTotal_amount());
                invoiceStmt.setTimestamp(4, Timestamp.valueOf(invoice.getInvoice_date()));

                var rs = invoiceStmt.executeQuery();
                if (rs.next()) {
                    int invoiceId = rs.getInt("invoice_id");
                    invoice.setInvoice_id(invoiceId);

                    for (Product product : invoice.getItems()) {
                        if (product.getProduct_id() <= 0) {
                            throw new SQLException("Invalid product_id (" + product.getProduct_id() + ") for product: " + product.getProduct_name());
                        }
                        itemStmt.setInt(1, invoiceId);
                        itemStmt.setInt(2, product.getProduct_id());
                        itemStmt.addBatch();
                    }
                    itemStmt.executeBatch();
                }
                connection.commit(); // Commit transaction
            } catch (Exception e) {
                connection.rollback(); // Rollback on error
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
