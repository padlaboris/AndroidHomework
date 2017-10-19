package com.example.padlabear.hw.json;


import org.json.JSONException;

import java.util.List;

public interface UserList<T> {
    List<T> getUsers() throws JSONException;
}
