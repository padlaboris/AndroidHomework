package com.example.parsejson.json.gson;


import com.example.parsejson.json.User;
import com.example.parsejson.util.DateUtil;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.Date;

public class GsonUser implements User {

    @SerializedName("id")
    private String id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("age")
    private String age;

    @SerializedName("location")
    private String location;

    @SerializedName("registered")
    private String registered;


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getAge() {
        return this.age;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public Date getRegistered() throws ParseException {
        return DateUtil.getDate(this.registered);
    }
}



