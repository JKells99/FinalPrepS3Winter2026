package com.finalreview.musicstore.product;

public class Instrument extends Product{
    private String instrument_type;
    private String instrument_brand;
    private int number_of_strings;
    private String instrument_color;

    public Instrument(int product_id, String product_name, String product_description, double product_price, int product_stock, String instrument_type, String instrument_brand, int number_of_strings, String instrument_color) {
        super(product_id, product_name, product_description, product_price, product_stock);
        this.instrument_type = instrument_type;
        this.instrument_brand = instrument_brand;
        this.number_of_strings = number_of_strings;
        this.instrument_color = instrument_color;
    }

    public Instrument(String product_name, String product_description, double product_price, int product_stock, String instrument_type, String instrument_brand, int number_of_strings, String instrument_color) {
        super(product_name, product_description, product_price, product_stock);
        this.instrument_type = instrument_type;
        this.instrument_brand = instrument_brand;
        this.number_of_strings = number_of_strings;
        this.instrument_color = instrument_color;
    }

    public Instrument(String instrument_type, String instrument_brand, int number_of_strings, String instrument_color) {
        this.instrument_type = instrument_type;
        this.instrument_brand = instrument_brand;
        this.number_of_strings = number_of_strings;
        this.instrument_color = instrument_color;
    }

    public String getInstrument_type() {
        return instrument_type;
    }

    public void setInstrument_type(String instrument_type) {
        this.instrument_type = instrument_type;
    }

    public String getInstrument_brand() {
        return instrument_brand;
    }

    public void setInstrument_brand(String instrument_brand) {
        this.instrument_brand = instrument_brand;
    }

    public int getNumber_of_strings() {
        return number_of_strings;
    }

    public void setNumber_of_strings(int number_of_strings) {
        this.number_of_strings = number_of_strings;
    }

    public String getInstrument_color() {
        return instrument_color;
    }

    public void setInstrument_color(String instrument_color) {
        this.instrument_color = instrument_color;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "instrument_type='" + instrument_type + '\'' +
                ", instrument_brand='" + instrument_brand + '\'' +
                ", number_of_strings=" + number_of_strings +
                ", instrument_color='" + instrument_color + '\'' +
                "} " + super.toString();
    }
}
