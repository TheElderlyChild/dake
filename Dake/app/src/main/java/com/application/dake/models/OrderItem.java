package com.application.dake.models;

public class OrderItem extends Model{
    private String name;
    private int amount;
    private double price;

    private String restaurantID;

    public OrderItem() {
    }

    public OrderItem(String name, int amount, double price, String restaurantID) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.restaurantID = restaurantID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }
}
