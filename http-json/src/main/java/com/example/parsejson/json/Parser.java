package com.example.parsejson.json;


public interface Parser<T> {
    T parse() throws Exception;

}
