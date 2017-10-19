package com.example.padlabear.hw.json.gson;

import com.example.padlabear.hw.json.Parser;
import com.google.gson.Gson;

public class GsonUserParser implements Parser<GsonUser> {

    private final String source;

    public GsonUserParser(final String source) {
        this.source = source;
    }

    @Override
    public GsonUser parse() throws Exception {
        return new Gson().fromJson(source, GsonUser.class);
    }
}
