package org.example;

import java.util.Stack;

public class Calculator {
    // метод, который создает обратную польскую нотацию
    public static String expressionToRPN(String preparedExpression) {
        String current = "";
        Stack<Character> stack = new Stack<>();

        int priority;
        for (int i = 0; i < preparedExpression.length(); i++) {
            priority = getPriority(preparedExpression.charAt(i));

            if (priority == 0) current += preparedExpression.charAt(i);
            if (priority == 1) stack.push(preparedExpression.charAt(i));
            if (priority > 1) {
                current += " ";

                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) current += stack.pop();
                    else break;
                }
                stack.push(preparedExpression.charAt(i));

            }
            if (priority == -1) {
                current += ' ';
                while (getPriority(stack.peek()) != 1) current += stack.pop();
                stack.pop();
            }
        }
        while (!stack.empty()) current += stack.pop();

        return current;
    }

    // метод, который подгатавливает выражение, если в исходном выражении содержатся отрицательные числа
    public static String preparingExpression(String expression) {
        String preparedExpression = new String();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-') {
                if (i == 0 || expression.charAt(i - 1) == '(') {
                    preparedExpression += '0';
                }
            }
            preparedExpression += expression.charAt(i);
        }
        return preparedExpression;
    }

    // метод, который из обратной польской нотации находит искомое число
    public static double rpnToAnswer(String rpn) {
        String operand = new String();
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') continue;
            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operand));
                operand = new String();
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                double a = stack.pop();
                double b = stack.pop();
                if (rpn.charAt(i) == '+') stack.push(b + a);
                if (rpn.charAt(i) == '-') stack.push(b - a);
                if (rpn.charAt(i) == '*') stack.push(b * a);
                if (rpn.charAt(i) == '/') stack.push(b / a);

            }
        }

        return stack.pop();
    }

    // находим приоритет символа из исходного выражения
    private static int getPriority(char sign) {
        if (sign == '*' || sign == '/') {
            return 3;
        } else if (sign == '+' || sign == '-') {
            return 2;
        } else if (sign == '(') {
            return 1;
        } else if (sign == ')') {
            return -1;
        } else return 0;
    }
}
