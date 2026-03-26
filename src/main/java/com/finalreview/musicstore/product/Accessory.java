package com.finalreview.musicstore.product;

public class Accessory extends Product{
    private String accessory_type;
    private String accessory_brand;

    public Accessory(int product_id, String product_name, String product_description, double product_price, int product_stock, String accessory_type, String accessory_brand) {
        super(product_id, product_name, product_description, product_price, product_stock);
        this.accessory_type = accessory_type;
        this.accessory_brand = accessory_brand;
    }

    public Accessory(String product_name, String product_description, double product_price, int product_stock, String accessory_type, String accessory_brand) {
        super(product_name, product_description, product_price, product_stock);
        this.accessory_type = accessory_type;
        this.accessory_brand = accessory_brand;
    }

    public Accessory(String accessory_type, String accessory_brand) {
        this.accessory_type = accessory_type;
        this.accessory_brand = accessory_brand;
    }

    public String getAccessory_type() {
        return accessory_type;
    }

    public void setAccessory_type(String accessory_type) {
        this.accessory_type = accessory_type;
    }

    public String getAccessory_brand() {
        return accessory_brand;
    }

    public void setAccessory_brand(String accessory_brand) {
        this.accessory_brand = accessory_brand;
    }
}
