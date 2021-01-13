package com.application.dake.models;

public class AccountItem {
    private String item;
    private int imageResource;

    public AccountItem(String item, int image) {
        this.imageResource=image;
        this.item=item;
    }
    public int getImageResource(){
        return imageResource;
    }
    public String getItem(){
        return item;
    }
}
