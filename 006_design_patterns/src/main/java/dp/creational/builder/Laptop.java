package dp.creational.builder;

import java.util.List;

public class Laptop {

    private String color;
    private String model;
    private String brand;
    private String display;
    private String resolution;
    private String ram;
    private String rom;
    private String cpu;
    private String chipset;
    private String keyboard;
    private List<String> ports;

    public Laptop withColor(final String newColor) {
        this.color = newColor;
        return this;
    }

    public Laptop withModel(final String asfodjsiofuigw9foFC) {
        this.model = asfodjsiofuigw9foFC;
        return this;
    }

    public Laptop withBrand(final String brand) {
        this.brand = brand;
        return this;
    }

    public Laptop withDisplay(final String display) {
        this.display = display;
        return this;
    }

}
