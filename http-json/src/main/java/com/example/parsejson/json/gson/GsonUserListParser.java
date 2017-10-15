package com.example.parsejson.json.gson;

import com.example.parsejson.json.Parser;
import com.google.gson.Gson;


public class GsonUserListParser implements Parser<GsonUserList> {

    private final String source;

    public GsonUserListParser(final String source) {
        this.source = source;
    }

    @Override
    public GsonUserList parse() throws Exception {
        return new Gson().fromJson(source, GsonUserList.class);
    }
}
