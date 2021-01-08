package com.application.dake.models;

import java.net.URI;
import java.util.List;

public class Restaurant {
    private String name;
    private Integer capacity;
    private String address;
    private Double rating;
    private String description;
    private URI imgURI;
    private String phoneNo;
    private List<MenuItem> menu;

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }


    public Restaurant() {
    }

    public Restaurant(String name, Integer capacity, String address, Double rating, String description, URI imgURI, String phoneNo) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
        this.rating = rating;
        this.description = description;
        this.imgURI = imgURI;
        this.phoneNo = phoneNo;
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

    public void setImgURI(URI imgURI) {
        this.imgURI = imgURI;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

    public URI getImgURI() {
        return imgURI;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

}
