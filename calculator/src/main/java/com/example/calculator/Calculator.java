package com.example.calculator;


import java.util.List;

public interface Calculator {

    String add(List<String> values);

    String multiply(List<String> values);

    String evaluate(String value);

}
