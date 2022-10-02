package org.example;

public class Main {
    public static void main(String[] args) {
        String preparedExpression = Calculator.preparingExpression("(-22)*(-22)");
        String rpn = Calculator.expressionToRPN(preparedExpression);
        double answer = Calculator.rpnToAnswer(rpn);
       System.out.println(answer);
    }
}
