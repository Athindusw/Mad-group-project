package com.example.madproject;


import java.sql.Time;
import java.util.Date;

public class tkAway {

        String name;
        Integer mobile;
        String date;
        String time;

    public tkAway(String name, Integer mobile, String date, String time) {
        this.name = name;
        this.mobile = mobile;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Integer getMobile() {
        return mobile;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}


