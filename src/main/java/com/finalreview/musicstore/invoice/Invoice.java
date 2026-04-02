package com.finalreview.musicstore.invoice;

import com.finalreview.musicstore.product.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private int invoice_id;
    private int customer_id;
    private int employee_id;
    private List<Product> items;
    private double total_amount;
    private LocalDateTime invoice_date = LocalDateTime.now();

    public Invoice(int customer_id, int employee_id, double total_amount, LocalDateTime invoice_date) {
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.items = new ArrayList<>();
        this.total_amount = total_amount;
        this.invoice_date = invoice_date;
    }
    public Invoice() {
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public LocalDateTime getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(LocalDateTime invoice_date) {
        this.invoice_date = invoice_date;
    }
    public void addItemToInvoice(Product item) {
        items.add(item);
    }
    public void removeItemFromInvoice(Product item) {
        items.remove(item);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Invoice{");
        sb.append("invoice_id=").append(invoice_id);
        sb.append(", customer_id=").append(customer_id);
        sb.append(", employee_id=").append(employee_id);
        sb.append(", items=").append(items);
        sb.append(", total_amount=").append(total_amount);
        sb.append(", invoice_date=").append(invoice_date);
        sb.append('}');
        return sb.toString();
    }
}
