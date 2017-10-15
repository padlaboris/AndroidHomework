package com.example.parsejson.json.jsonObject;


import com.example.parsejson.json.Parser;

import org.json.JSONObject;

public class JsonUserListParser implements Parser<JsonUserList> {

    private final String source;

    public JsonUserListParser(final String source) {
        this.source = source;
    }

    @Override
    public JsonUserList parse() throws Exception {
        final JSONObject jsonObject = new JSONObject(source);
        return new JsonUserList(jsonObject);
    }
}
