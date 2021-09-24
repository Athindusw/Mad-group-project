package com.example.madproject;

public class dlvry {
    String name;
    Integer mobile;
    String address;
    String distance;
    String date;
    String time;

    public dlvry(String name, Integer mobile, String address, String distance, String date, String time) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.distance = distance;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Integer getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getDistance() {
        return distance;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
