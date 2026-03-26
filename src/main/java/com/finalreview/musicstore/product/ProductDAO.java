package com.finalreview.musicstore.product;

import com.finalreview.musicstore.database.DBConnection;

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


    public List<Instrument> getAllInstruments() {
        String sql = "SELECT * FROM instruments JOIN products ON instruments.product_id = products.product_id";
        List<Instrument> instruments = new ArrayList<>();
        try (var connection = DBConnection.getConnection();
             var stmt = connection.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            if (rs.next()) {
                Instrument instrument=  new Instrument(rs.getString("instrument_type"), rs.getString("instrument_brand"), rs.getInt("number_of_strings"), rs.getString("instrument_color"));
                instruments.add(instrument);

            }
            return instruments;
        } catch (Exception e) {
            e.printStackTrace();
        }


    return null;
    }
}