package com.example.parsejson.json;


import org.json.JSONException;

import java.util.List;

public interface UserList<T> {
    List<T> getUsers() throws JSONException;
}
