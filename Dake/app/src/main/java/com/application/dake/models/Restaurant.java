package com.application.dake.models;

import java.util.List;

public class Restaurant {
    private String name;
    private Integer capacity;
    private String address;
    private Double rating;
    private String description;
    private String imageUrl;
    private String phoneNo;
    private String type;
    private List<MenuItem> menu;

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }


    public Restaurant() {
    }

    public Restaurant(String name, Integer capacity, String address, Double rating, String description, String imageUrl, String phoneNo, String type) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
        this.rating = rating;
        this.description = description;
        this.imageUrl = imageUrl;
        this.phoneNo = phoneNo;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getAddress() {
        return address;
    }

    public Double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getType() {
        return type;
    }

    public String toString() {return name + " " + description;}
}
