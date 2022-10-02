package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void expressionToRPN() {
        String actual = Calculator.expressionToRPN("2+2*2");
        String expected = "2 2 2*+";
        assertEquals(expected, actual);

    }

    @Test
    public void preparingExpression() {
        String actual = Calculator.preparingExpression("-(-5)*(-3)");
        String expected = "0-(0-5)*(0-3)";
        assertEquals(expected, actual);
    }

    @Test
    public void rpnToAnswer() {
        Double actual = Calculator.rpnToAnswer("2 2 2*+");
        Double expected = 6.0;
        assertEquals(expected, actual);

    }
}