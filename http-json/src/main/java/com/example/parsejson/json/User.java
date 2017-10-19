package com.example.parsejson.json;


import java.text.ParseException;
import java.util.Date;

public interface User {

    String getId();

    String getFirstName();

    String getLastName();

    String getAge();

    String getLocation();

    Date getRegistered() throws ParseException;

}
