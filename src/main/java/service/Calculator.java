package service;

import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {

    private final String SIGN_PLUS = "+";
    private final String SIGN_MINUS = "-";
    private final String SIGN_MULTIPLICATION = "*";
    private final String SIGN_DIVISION = "/";
    private final String SIGN_DEGREE = "^";
    private final String OPEN_BRACKET = "(";
    private final String CLOSE_BRACKET = ")";

    private ExpressionHandler expressionHandler;

    public Calculator(ExpressionHandler expressionHandler) {
        this.expressionHandler = expressionHandler;
    }

    private final String AVAILABLE_FUNCTIONS = SIGN_PLUS + SIGN_MINUS + SIGN_MULTIPLICATION + SIGN_DIVISION
            + SIGN_DEGREE;

    public double calc(String expression) {

        StringTokenizer expressionTokens = new StringTokenizer(expressionHandler.prepare(expression),
                AVAILABLE_FUNCTIONS + OPEN_BRACKET + CLOSE_BRACKET, true);

        Stack<String> operands = new Stack();
        Stack<String> functions = new Stack();

        while (expressionTokens.hasMoreTokens()) {

            String token = expressionTokens.nextToken();

            if (isNumber(token)) {
                operands.push(token);
                continue;
            }
            if (isOpenBracket(token)) {
                functions.push(token);
                continue;
            }
            if (!isFunction(token) && !token.equals(CLOSE_BRACKET)) {
                throw new IllegalArgumentException(
                        String.format("Please check your input. The token is unacceptable: '%s'", token));
            }

            if (functions.size() > 0) {
                if (getPrecedence(functions.lastElement()) <= getPrecedence(token)) {
                    if (isCloseBracket(token)) {
                        String currentFunction;
                        while (!isOpenBracket((currentFunction = functions.pop()))) {
                            operands.push(doFunction(operands.pop(), operands.pop(), currentFunction));
                        }
                    } else {
                        operands.push(doFunction(operands.pop(), operands.pop(), functions.pop()));
                        functions.push(token);
                    }
                } else {
                    functions.push(token);
                }
            } else {
                functions.push(token);
            }
        }

        return Double.parseDouble(operands.get(0));
    }

    private int getPrecedence(String operator) {
        if (operator.equals(SIGN_PLUS) || operator.equals(SIGN_MINUS)) {
            return 3;
        }
        if (operator.equals(SIGN_MULTIPLICATION) || operator.equals(SIGN_DIVISION)) {
            return 2;
        }
        if (operator.equals(SIGN_DEGREE)) {
            return 1;
        }
        if (isOpenBracket(operator)) {
            return 4;
        }
        if (isCloseBracket(operator)) {
            return 4;
        }
        throw new IllegalArgumentException(String.format("Operator %s is unacceptable", operator));
    }

    private String doFunction(String b, String a, String operator) {

        double numbA = Double.parseDouble(a);
        double numbB = Double.parseDouble(b);

        if (operator.equals(SIGN_MINUS)) {
            return String.valueOf(numbA - numbB);
        }
        if (operator.equals(SIGN_PLUS)) {
            return String.valueOf(numbA + numbB);
        }
        if (operator.equals(SIGN_MULTIPLICATION)) {
            return String.valueOf(numbA * numbB);
        }
        if (operator.equals(SIGN_DIVISION)) {
            return String.valueOf(numbA / numbB);
        }
        if (operator.equals(SIGN_DEGREE)) {
            return String.valueOf(Math.pow(numbA, numbB));
        }

        throw new IllegalArgumentException(String.format("Please check your input. Function '%s' is not available", operator));
    }


    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }

    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }

    private boolean isFunction(String token) {
        return AVAILABLE_FUNCTIONS.contains(token);
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}