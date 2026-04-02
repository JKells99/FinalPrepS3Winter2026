package com.finalreview.musicstore.invoice;

import com.finalreview.musicstore.product.Product;

import java.time.LocalDateTime;
import java.util.List;

public class InvoiceService {
    private final InvoiceDAO invoiceDAO;

    public InvoiceService() {
        this.invoiceDAO = new InvoiceDAO();
    }

    public Invoice processPurchase(int customerId, int employeeId, List<Product> items) {
        double totalAmount = items.stream()
                .mapToDouble(Product::getProduct_price)
                .sum();

        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customerId);
        invoice.setEmployee_id(employeeId);
        invoice.setItems(items);
        invoice.setTotal_amount(totalAmount);
        invoice.setInvoice_date(LocalDateTime.now());

        invoiceDAO.saveInvoiceToDb(invoice);
        return invoice;
    }

    public void printInvoice(Invoice invoice) {
    StringBuffer sb = new StringBuffer();
    sb.append("Invoice Details: ");
    sb.append(invoice.getInvoice_id()).append("\n");
    sb.append("====================\n");
    sb.append("Invoice ID: ").append(invoice.getInvoice_id()).append("\n");
    sb.append("Customer ID: ").append(invoice.getCustomer_id()).append("\n");
    sb.append("Employee ID: ").append(invoice.getEmployee_id()).append("\n");
    sb.append("Items: ").append(invoice.getItems()).append("\n");
    sb.append("Total Amount: ").append(invoice.getTotal_amount()).append("\n");
    sb.append("Invoice Date: ").append(invoice.getInvoice_date()).append("\n");
    sb.append("====================\n");
    System.out.println(sb.toString());
    }

}
