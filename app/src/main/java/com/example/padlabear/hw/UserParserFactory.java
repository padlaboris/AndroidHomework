package com.example.padlabear.hw;

import com.example.padlabear.hw.json.gson.GsonUserListParser;
import com.example.padlabear.hw.json.gson.GsonUserParser;

public class UserParserFactory {

    public GsonUserParser createUserParser(final String source) {
        return new GsonUserParser(source);
    }

    public GsonUserListParser createUsersParser(final String source) {
        return new GsonUserListParser(source);
    }
}
