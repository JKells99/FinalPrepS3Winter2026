package com.finalreview.musicstore.product;

import java.util.Collections;
import java.util.List;

public class ProductService {


    private static ProductDAO productDAO = new ProductDAO();


    public void saveInstrument(Instrument instrument) {
        if(instrument.getInstrument_type().isEmpty() || instrument.getInstrument_brand().isEmpty() || instrument.getNumber_of_strings() <= 0 || instrument.getInstrument_color().isEmpty()) {
            System.out.println("Invalid instrument details. Please provide all required information.");
            return;
        }
        productDAO.saveInstrumentToDb(instrument);
        System.out.println("Instrument saved to database: " + instrument.getProduct_name());
    }

    public void saveAccessory(Accessory accessory) {
        if(accessory.getAccessory_type().isEmpty() || accessory.getAccessory_brand().isEmpty()) {
            System.out.println("Invalid accessory details. Please provide all required information.");
            return;
        }
        productDAO.saveAccessoryToDb(accessory);
        System.out.println("Accessory saved to database: " + accessory.getProduct_name());
    }
    public List<Instrument> getAllInstruments() {
        return getAllProducts("instrument", Instrument.class);
    }

    public List<Accessory> getAllAccessories() {
        return getAllProducts("accessory", Accessory.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends Product> List<T> getAllProducts(String type, Class<T> clazz) {
        List<T> products;
        if (type.equalsIgnoreCase("instrument")) {
            products = (List<T>) productDAO.getAllInstruments();
        } else if (type.equalsIgnoreCase("accessory")) {
            products = (List<T>) productDAO.getAllAccessories();
        } else {
            return Collections.emptyList();
        }

        if (products.isEmpty()) {
            System.out.println("No " + type + "s found in inventory.");
        } else {
            System.out.println("--- Current " + type.toUpperCase() + "S ---");
            for (T product : products) {
                System.out.println(product);
            }
        }
        return products;
    }
}



