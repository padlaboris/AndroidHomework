package com.example.calculator;


import java.util.Arrays;
import java.util.List;

public class DefaultCalculator implements Calculator {

    private static String PLUS = "+";
    private static String MULTIPLY = "*";

    @Override
    public String add(List<String> values) {
        Integer sum = 0;
        for (String value : values) {
            sum += Integer.parseInt(value);
        }
        return sum.toString();
    }

    @Override
    public String multiply(List<String> values) {
        Integer sum = 1;
        for (String value : values) {
            sum *= Integer.parseInt(value);
        }
        return sum.toString();
    }

    @Override
    public String evaluate(String value) {
        return parseExpression(value);
    }

//TODO check if input values are letters or other symbols
    private String parseExpression(String exp) {
        final String result;
        final List<String> numbers;

        if (exp.contains(PLUS)) {
            numbers = Arrays.asList(exp.split("\\+"));
            result = add(numbers);
        } else if (exp.contains(MULTIPLY)) {
            numbers = Arrays.asList(exp.split("\\*"));
            result = multiply(numbers);
        } else {
            result = "Неверное выражение";
        }
        return result;
    }
}
