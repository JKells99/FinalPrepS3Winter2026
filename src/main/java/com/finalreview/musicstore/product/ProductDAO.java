package com.finalreview.musicstore.product;

import com.finalreview.musicstore.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void saveInstrumentToDb(Instrument instrument) {
        String productSql = "INSERT INTO products (product_name, product_description, product_price, product_stock) VALUES (?, ?, ?, ?) RETURNING product_id";
        String instrumentSql = "INSERT INTO instruments (product_id, instrument_type, instrument_brand, number_of_strings, instrument_color) VALUES (?, ?, ?, ?, ?)";

        try (var connection = DBConnection.getConnection();
             var productStmt = connection.prepareStatement(productSql);
             var instrumentStmt = connection.prepareStatement(instrumentSql)) {

            // Save product details and get generated product_id
            productStmt.setString(1, instrument.getProduct_name());
            productStmt.setString(2, instrument.getProduct_description());
            productStmt.setDouble(3, instrument.getProduct_price());
            productStmt.setInt(4, instrument.getProduct_stock());

            var rs = productStmt.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("product_id");
                ;
                // Save instrument details using the generated product_id
                instrumentStmt.setInt(1, productId);
                instrumentStmt.setString(2, instrument.getInstrument_type());
                instrumentStmt.setString(3, instrument.getInstrument_brand());
                instrumentStmt.setInt(4, instrument.getNumber_of_strings());
                instrumentStmt.setString(5, instrument.getInstrument_color());
                instrumentStmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAccessoryToDb(Accessory accessory) {
        String productSql = "INSERT INTO products (product_name, product_description, product_price, product_stock) VALUES (?, ?, ?, ?) RETURNING product_id";
        String accessorySql = "INSERT INTO accessories (product_id, accessory_type, accessory_brand) VALUES (?, ?, ?)";

        try (var connection = DBConnection.getConnection();
             var productStmt = connection.prepareStatement(productSql);
             var accessoryStmt = connection.prepareStatement(accessorySql)) {

            // Save product details and get generated product_id
            productStmt.setString(1, accessory.getProduct_name());
            productStmt.setString(2, accessory.getProduct_description());
            productStmt.setDouble(3, accessory.getProduct_price());
            productStmt.setInt(4, accessory.getProduct_stock());

            var rs = productStmt.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("product_id");
                ;
                // Save accessory details using the generated product_id
                accessoryStmt.setInt(1, productId);
                accessoryStmt.setString(2, accessory.getAccessory_type());
                accessoryStmt.setString(3, accessory.getAccessory_brand());
                accessoryStmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public List<Product> getAllProductsRepeat() {
//        String sql = "SELECT * FROM products p LEFT JOIN instruments i ON p.product_id = i.product_id LEFT JOIN accessories a ON p.product_id = a.product_id";
//        List<Product> products = new ArrayList<>();
//        try (var connection = DBConnection.getConnection();
//             var stmt = connection.prepareStatement(sql);
//             var rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                if (rs.getString("instrument_type") != null) {
//                    products.add(new Instrument(
//                            rs.getInt("product_id"),
//                            rs.getString("product_name"),
//                            rs.getString("product_description"),
//                            rs.getDouble("product_price"`),
//                            rs.getInt("product_stock"),)
//
//
//    }

    // Change it up a bit
    @FunctionalInterface
    private interface RowMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    private <T extends Product> List<T> fetchList(String sql, RowMapper<T> mapper) {
        List<T> list = new ArrayList<>();
        try (var connection = DBConnection.getConnection();
             var stmt = connection.prepareStatement(sql);
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }
        return list;
    }

    public List<Instrument> getAllInstruments() {
        String sql = "SELECT * FROM products p JOIN instruments i ON p.product_id = i.product_id";
        return fetchList(sql, this::mapRowToInstrument);
    }

    public List<Accessory> getAllAccessories() {
        String sql = "SELECT * FROM products p JOIN accessories a ON p.product_id = a.product_id";
        return fetchList(sql, this::mapRowToAccessory);
    }

    private Instrument mapRowToInstrument(ResultSet rs) throws SQLException {
        return new Instrument(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getString("product_description"),
                rs.getDouble("product_price"),
                rs.getInt("product_stock"),
                rs.getString("instrument_type"),
                rs.getString("instrument_brand"),
                rs.getInt("number_of_strings"),
                rs.getString("instrument_color")
        );
    }

    private Accessory mapRowToAccessory(ResultSet rs) throws SQLException {
        return new Accessory(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getString("product_description"),
                rs.getDouble("product_price"),
                rs.getInt("product_stock"),
                rs.getString("accessory_type"),
                rs.getString("accessory_brand")
        );
    }
}