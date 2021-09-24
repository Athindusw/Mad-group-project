package com.example.madproject;

public class RecyclerRice {
    int image;
    String name;
    Double price;


    public RecyclerRice(int image, String name, Double price) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
