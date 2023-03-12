/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import java.io.File;

/**
 *
 * @author PC
 */
public class ProductDTO {
    private int id;
    private String brand;
    private String type;
    private double price;
    private int sale;
    private int stock;
    private String ageGroup;
    private String size;
    private String color;

    public ProductDTO() {
    }

    public ProductDTO(int id, String brand, String type, double price, int sale, int stock, String ageGroup, String size, String color) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.sale = sale;
        this.stock = stock;
        this.ageGroup = ageGroup;
        this.size = size;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getSale() {
        return sale;
    }

    public int getStock() {
        return stock;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }    
}
