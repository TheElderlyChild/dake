package com.application.dake.models;

import android.location.Address;

import java.time.LocalTime;

public class Restaurant {
    private String name;
    private Integer capacity;
    private Address address;
    private Float rating;
    private LocalTime startTime;
    private LocalTime closeTime;

    public Restaurant(String name, Integer capacity, Address address, LocalTime startTime, LocalTime closeTime) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
        this.startTime = startTime;
        this.closeTime = closeTime;
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Address getAddress() {
        return address;
    }

    public Float getRating() {
        return rating;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }
}
