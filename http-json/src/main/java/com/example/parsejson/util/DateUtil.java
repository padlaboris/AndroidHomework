package com.example.parsejson.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    final private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static Date getDate(final String expression) throws ParseException {
        return formatter.parse(expression);
    }
}
