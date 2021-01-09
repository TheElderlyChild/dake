package com.application.dake.models;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Order {
    private Restaurant restaurant;
    private FirebaseUser customer;
    private String type;
    private List<OrderItem> items;

    public Order() {
    }

    public Order(Restaurant restaurant, FirebaseUser customer, String type) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.type = type;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public FirebaseUser getCustomer() {
        return customer;
    }

    public void setCustomer(FirebaseUser customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

}
