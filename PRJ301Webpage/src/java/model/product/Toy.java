/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import java.util.Date;

/**
 *
 * @author PHT
 * JavaBean
 * Entity class: là lớp tương ứng với một table trong db
 */
public class Toy {
    private String id;
    private String name;
    private double price;
    private Date expDate;
    private String brandId;

    public Toy() {
    }

    public Toy(String id, String name, double price, Date expDate, String brandId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expDate = expDate;
        this.brandId = brandId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    
    
}
