package com.example.madproject;

public class Food {

    private String foodCategory;
    private String foodImage;
    private String foodName;
    private String foodPrice;

    public Food() {
    }

    public Food(String foodCategory, String foodImage, String foodName, String foodPrice) {
        this.foodCategory = foodCategory;
        this.foodImage = foodImage;
        this.foodName = foodName;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }
}
