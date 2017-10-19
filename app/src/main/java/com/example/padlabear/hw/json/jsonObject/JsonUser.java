package com.example.padlabear.hw.json.jsonObject;


import com.example.padlabear.hw.json.User;
import com.example.padlabear.hw.util.DateUtil;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

public class JsonUser implements User {

    public static final String ID = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String AGE = "age";
    public static final String LOCATION = "location";
    public static final String REGISTERED = "registered";

    private final JSONObject jsonObject;

    public JsonUser(final JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String getId() {
        return this.jsonObject.optString(ID);
    }

    @Override
    public String getFirstName() {
        return this.jsonObject.optString(FIRST_NAME);
    }

    @Override
    public String getLastName() {
        return this.jsonObject.optString(LAST_NAME);
    }

    @Override
    public String getAge() {
        return this.jsonObject.optString(AGE);
    }

    @Override
    public String getLocation() {
        return this.jsonObject.optString(LOCATION);
    }

    @Override
    public Date getRegistered() throws ParseException {
        return DateUtil.getDate(jsonObject.optString(REGISTERED));
    }
}
