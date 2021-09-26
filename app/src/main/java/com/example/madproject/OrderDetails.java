package com.example.madproject;

public class OrderDetails {
    private String Price;
    private String ProductName;
    private String Quantity;

    public OrderDetails() {
    }

    public OrderDetails(String price, String productName, String quantity) {
        Price = price;
        ProductName = productName;
        Quantity = quantity;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }




}
