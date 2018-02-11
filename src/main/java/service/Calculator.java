package service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.EmptyStackException;
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

    private final String AVAILABLE_FUNCTIONS = SIGN_PLUS + SIGN_MINUS + SIGN_MULTIPLICATION + SIGN_DIVISION
            + SIGN_DEGREE;

    private final int PRECENDENCE_SUM_AND_DIFFERENCE = 1;
    private final int PRECENDENCE_DIVISION_AND_MULTIPLICATION = 2;
    private final int PRECENDENCE_DEGREE = 3;

    private final int ACCURACY = 15;

    private ExpressionHandler expressionHandler;

    public Calculator(ExpressionHandler expressionHandler) {
        this.expressionHandler = expressionHandler;
    }


    public String calc(String expression) {

        Stack<String> operands = new Stack();
        Stack<String> functions = new Stack();

        StringTokenizer expressionTokens = new StringTokenizer(expressionHandler.prepare(expression),
                AVAILABLE_FUNCTIONS + OPEN_BRACKET + CLOSE_BRACKET, true);

        String result;
        try {
            result = solve(operands, functions, expressionTokens);
        } catch (EmptyStackException ex){
            throw new IllegalArgumentException("Wrong count of mathematical signs.");
        }
        return result;
    }

    private String solve(Stack<String> operands, Stack<String> functions, StringTokenizer expressionTokens) {

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
                        String.format("The token is unacceptable: '%s'", token));
            }

            if (functions.size() > 0) {
                if (getPrecedence(functions.lastElement()) >= getPrecedence(token)) {
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

        if (operands.size() == 1 && functions.size() == 0) {
            return new BigDecimal(operands.get(0)).stripTrailingZeros().toPlainString();
        } else {
            throw new RuntimeException("Wrong count of open and close brackets.");
        }
    }

    private int getPrecedence(String operator) {
        if (operator.equals(SIGN_PLUS) || operator.equals(SIGN_MINUS)) {
            return PRECENDENCE_SUM_AND_DIFFERENCE;
        }
        if (operator.equals(SIGN_MULTIPLICATION) || operator.equals(SIGN_DIVISION)) {
            return PRECENDENCE_DIVISION_AND_MULTIPLICATION;
        }
        if (operator.equals(SIGN_DEGREE)) {
            return PRECENDENCE_DEGREE;
        }
        if (isOpenBracket(operator) || isCloseBracket(operator)) {
            return 0;
        }
        throw new IllegalArgumentException(String.format("Operator %s is unacceptable", operator));
    }

    private String doFunction(String b, String a, String operator) {

        BigDecimal numbA = new BigDecimal(a);
        BigDecimal numbB = new BigDecimal(b);

        if (operator.equals(SIGN_MINUS)) {
            return String.valueOf(numbA.subtract(numbB).toString());
        }
        if (operator.equals(SIGN_PLUS)) {
            return String.valueOf(numbA.add(numbB).toString());
        }
        if (operator.equals(SIGN_MULTIPLICATION)) {
            return String.valueOf(numbA.multiply(numbB).toString());
        }
        if (operator.equals(SIGN_DIVISION)) {
            return String.valueOf(numbA.divide(numbB, ACCURACY,RoundingMode.HALF_UP).toString());
        }
        if (operator.equals(SIGN_DEGREE)) {
            return String.valueOf(numbA.pow(Integer.valueOf(b), MathContext.DECIMAL128));
        }

        throw new IllegalArgumentException(String.format("Function '%s' is not available", operator));
    }

    private boolean isOpenBracket(String token) {
        return token.equals(OPEN_BRACKET);
    }

    private boolean isCloseBracket(String token) {
        return token.equals(CLOSE_BRACKET);
    }

    private boolean isFunction(String token) {
        return AVAILABLE_FUNCTIONS.contains(token);
    }

    private boolean isNumber(String token) {
        try {
            new BigDecimal(token);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}