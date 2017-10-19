package com.example.padlabear.hw.calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = spy(DefaultCalculator.class);
    }

    @Test
    public void addEmptyResultTest() {
        final List<String> numbers = Collections.emptyList();
        final String result = calculator.add(numbers);
        verify(calculator).add(numbers);
        assertEquals("0", result);
    }

    @Test
    public void addNotEmptyResultTest() {
        final List<String> numbers = Arrays.asList("2", "2");
        final String result = calculator.add(numbers);
        verify(calculator).add(numbers);
        assertEquals("4", result);
    }

    @Test
    public void multiplyEmptyResultTest() {
        final List<String> numbers = Collections.emptyList();
        final String result = calculator.multiply(numbers);
        verify(calculator).multiply(numbers);
        assertEquals("1", result);
    }

    @Test
    public void multiplyNotEmptyResultTest() {
        final List<String> numbers = Arrays.asList("4", "5");
        final String result = calculator.multiply(numbers);
        verify(calculator).multiply(numbers);
        assertEquals("20", result);
    }

    @Test
    public void evaluateSumResult() {
        final String exp = "2+2";
        final String result = calculator.evaluate(exp);
        verify(calculator).evaluate(exp);
        verify(calculator).add(anyList());
        assertEquals("4", result);
    }

    @Test
    public void evaluateMultiplyResult() {
        final String exp = "3*3";
        final String result = calculator.evaluate(exp);
        verify(calculator).evaluate(exp);
        verify(calculator).multiply(anyList());
        assertEquals("9", result);
    }
}