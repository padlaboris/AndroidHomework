package com.example.padlabear.hw.json.jsonObject;


import com.example.padlabear.hw.json.UserList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUserList implements UserList<JsonUser> {

    public static final String USERS = "users";
    private final List<JsonUser> users = new ArrayList<>();
    private JSONArray jsonArray;

    public JsonUserList(final JSONObject jsonObject) throws JSONException {
        this.jsonArray = jsonObject.getJSONArray(USERS);
    }

    @Override
    public List<JsonUser> getUsers() throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            users.add(new JsonUser(jsonArray.getJSONObject(i)));
        }
        return users;
    }
}
