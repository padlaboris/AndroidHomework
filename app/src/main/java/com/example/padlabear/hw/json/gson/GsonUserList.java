package com.example.padlabear.hw.json.gson;


import com.example.padlabear.hw.json.UserList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GsonUserList implements UserList<GsonUser> {

    @SerializedName("users")
    private List<GsonUser> users;

    public GsonUserList(List<GsonUser> users) {
        this.users = users;
    }

    @Override
    public List<GsonUser> getUsers() {
        return users;
    }
}