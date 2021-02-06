package com.walmart.dto;

import org.springframework.data.annotation.Id;

import java.util.StringJoiner;

public class Product {

    @Id
    private long id;

    private String brand;
    private String description;
    private String image;
    private double price;
    private double onSalePrice;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOnSalePrice() {
        return onSalePrice;
    }

    public void setOnSalePrice(double onSalePrice) {
        this.onSalePrice = onSalePrice;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("brand='" + brand + "'")
                .add("description='" + description + "'")
                .add("image='" + image + "'")
                .add("price=" + price)
                .add("onSalePrice=" + onSalePrice)
                .toString();
    }
}
