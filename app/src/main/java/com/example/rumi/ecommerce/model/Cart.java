package com.example.rumi.ecommerce.model;

public class Cart {

    private int id;
    private int quantity;
    private String name;
    private double price;

    public Cart() {
    }

    public Cart(int id, String name, double price, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
