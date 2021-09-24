package com.example.madproject;

import android.widget.Button;

public class RecyclerFood {
    int image;
    String name;
    double price;

    public RecyclerFood(int image, String name, double price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
