package com.example.padlabear.hw.json.jsonObject;


import com.example.padlabear.hw.json.Parser;

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
