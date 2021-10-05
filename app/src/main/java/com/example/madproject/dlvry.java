package com.example.madproject;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class dlvry implements Serializable {
    @Exclude
    String id;
    String name;
    String mobile;
    String address;
    String distance;
    String date;
    String time;

    public dlvry() {
    }

    public dlvry(String name, String mobile, String address, String distance, String date, String time) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.distance = distance;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
