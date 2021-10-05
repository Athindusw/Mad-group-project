package com.example.madproject;


import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class tkAway implements Serializable {
        @Exclude
        String id;
        String name;
        String mobile;
        String date;
        String time;

    public tkAway() {

    }

    public tkAway(String name, String mobile, String date, String time) {
        this.name = name;
        this.mobile = mobile;
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

    public String getMobile() {
        return mobile;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


