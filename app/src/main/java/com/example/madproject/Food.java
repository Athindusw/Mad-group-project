package com.example.madproject;

public class Food {
    private String foodName;
    private Double foodPrice;
    private String foodCategory;
    private String foodImage;

    public Food() {
    }

    public Food(String foodName, Double foodPrice, String foodCategory, String foodImage) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodCategory = foodCategory;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }
}
