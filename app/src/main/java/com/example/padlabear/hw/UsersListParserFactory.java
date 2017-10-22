package com.example.padlabear.hw;


import com.example.padlabear.hw.json.Parser;
import com.example.padlabear.hw.json.gson.GsonUserListParser;

import java.io.InputStream;

public class UsersListParserFactory {
    public Parser createParser(InputStream psource) throws Exception {
        return new GsonUserListParser(psource);
    }

    public Parser createParserforResponceWithObject(InputStream psource) {
        return new
    }
}
