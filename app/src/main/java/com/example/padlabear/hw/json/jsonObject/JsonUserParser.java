package com.example.padlabear.hw.json.jsonObject;


import com.example.padlabear.hw.json.Parser;

import org.json.JSONObject;

public class JsonUserParser implements Parser<JsonUser> {

    private final String source;

    public JsonUserParser(final String source) {
        this.source = source;
    }

    @Override
    public JsonUser parse() throws Exception {
        final JSONObject jsonObject = new JSONObject(source);
        return new JsonUser(jsonObject);
    }
}
