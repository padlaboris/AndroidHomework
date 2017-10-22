package com.example.padlabear.hw.json.gson;

import com.example.padlabear.hw.json.Parser;
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
