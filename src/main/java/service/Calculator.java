package service;

import utils.Props;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

public class Calculator {

    private final String SIGN_PLUS = Props.read("sign.plus");
    private final String SIGN_MINUS = Props.read("sign.minus");
    private final String SIGN_MULTIPLICATION = Props.read("sign.multiplication");
    private final String SIGN_DIVISION = Props.read("sign.division");
    private final String SIGN_DEGREE = Props.read("sign.degree");
    private final String CLOSE_BRACKET = Props.read("close.bracket");
    private final String OPEN_BRACKET = Props.read("open.bracket");
    private final int ACCURACY = Props.readInt("accuracy");
    private final Map<String, Integer> AVAILABLE_FUNCTIONS;

    private ExpressionHandler expressionHandler;

    public Calculator(ExpressionHandler expressionHandler) {
        this.expressionHandler = expressionHandler;
        this.AVAILABLE_FUNCTIONS = new HashMap<>();
        AVAILABLE_FUNCTIONS.put(SIGN_MINUS, Props.readInt("precedence.difference"));
        AVAILABLE_FUNCTIONS.put(SIGN_PLUS, Props.readInt("precedence.sum"));
        AVAILABLE_FUNCTIONS.put(SIGN_DIVISION, Props.readInt("precedence.division"));
        AVAILABLE_FUNCTIONS.put(SIGN_MULTIPLICATION, Props.readInt("precedence.multiplication"));
        AVAILABLE_FUNCTIONS.put(SIGN_DEGREE, Props.readInt("precedence.degree"));
    }

    public String calc(String expression) {

        expression = expressionHandler.prepare(expression);

        String rpn = receiveReversePolishNotationString(expression);

        StringTokenizer rpnTokenizer = new StringTokenizer(rpn, " ");
        Stack<BigDecimal> operands = new Stack<>();
        while (rpnTokenizer.hasMoreTokens()) {

            String currentToken = rpnTokenizer.nextToken();

            if (isBrackets(currentToken)) {
                throw new IllegalArgumentException("Wrong count of opening and closing brackets. " +
                        "Number of open brackets > number of closing brackets");
            }

            if (!isFunction(currentToken)) {
                operands.push(toBigDecimal(currentToken));
            } else {
                try {
                    operands.push(doFunction(operands.pop(), operands.pop(), currentToken));
                } catch (EmptyStackException ex) {
                    throw new IllegalArgumentException("Invalid expression.");
                }
            }
        }

        if (operands.size() != 1) {
            throw new IllegalArgumentException("Invalid expression.");
        }

        return operands.pop().stripTrailingZeros().toPlainString();
    }

    private boolean isBrackets(String token) {
        return token.equals(OPEN_BRACKET) || token.equals(CLOSE_BRACKET);
    }

    private boolean isFunction(String token) {
        return AVAILABLE_FUNCTIONS.keySet().contains(token);
    }

    private BigDecimal toBigDecimal(String token) {
        try {
            return new BigDecimal(token);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(String.format("The token is unacceptable: '%s'", token));
        }
    }

    private BigDecimal doFunction(BigDecimal operandA, BigDecimal operandB, String functionToken) {
        if (functionToken.equals(SIGN_MULTIPLICATION)) {
            return operandB.multiply(operandA);
        } else if (functionToken.equals(SIGN_DIVISION)) {
            return operandB.divide(operandA, ACCURACY, RoundingMode.HALF_UP);
        } else if (functionToken.equals(SIGN_PLUS)) {
            return operandB.add(operandA);
        } else if (functionToken.equals(SIGN_MINUS)) {
            return operandB.subtract(operandA);
        } else if (functionToken.equals(SIGN_DEGREE)) {
            return operandB.pow(operandA.intValue(), MathContext.DECIMAL128);
        }
        throw new IllegalArgumentException(String.format("Function '%s' is not available", functionToken));
    }

    private String receiveReversePolishNotationString(String expression) {
        try {
            return doSortingStation(expression);
        } catch (EmptyStackException ex) {
            throw new IllegalArgumentException("Wrong count of opening and closing brackets. " +
                    "Number of open brackets < number of closing brackets");
        }
    }

    private String doSortingStation(String expression) {

        final Set<String> functionSymbols = new HashSet<>(AVAILABLE_FUNCTIONS.keySet());
        functionSymbols.add(OPEN_BRACKET);
        functionSymbols.add(CLOSE_BRACKET);

        List<String> out = new ArrayList<>();

        Stack<String> functions = new Stack<>();

        int index = 0;
        while (true) {
            int nextFunctionIndex = expression.length();
            String nextFunction = "";
            for (String function : functionSymbols) {
                int i = expression.indexOf(function, index);
                if (i >= 0 && i < nextFunctionIndex) {
                    nextFunction = function;
                    nextFunctionIndex = i;
                }
            }
            if (nextFunctionIndex == expression.length()) {
                break;
            } else {
                if (index != nextFunctionIndex) {
                    out.add(expression.substring(index, nextFunctionIndex));
                }
                if (isOpeningBracket(nextFunction)) {
                    functions.push(nextFunction);
                } else if (isClosingBracket(nextFunction)) {
                    while (!isOpeningBracket(functions.peek())) {
                        out.add(functions.pop());
                        if (functions.empty()) {
                            throw new IllegalArgumentException("Wrong count of brackets");
                        }
                    }
                    functions.pop();
                } else {
                    while (!functions.empty() && !isOpeningBracket(functions.peek()) &&
                            (AVAILABLE_FUNCTIONS.get(nextFunction) <= AVAILABLE_FUNCTIONS.get(functions.peek()))) {
                        out.add(functions.pop());
                    }
                    functions.push(nextFunction);
                }
                index = nextFunctionIndex + nextFunction.length();
            }
        }

        if (index != expression.length()) {
            out.add(expression.substring(index));
        }

        while (!functions.empty()) {
            out.add(functions.pop());
        }
        StringBuilder result = new StringBuilder();
        if (!out.isEmpty()) {
            result.append(out.remove(0));
        }
        while (!out.isEmpty()) {
            result.append(" ").append(out.remove(0));
        }

        return result.toString();
    }

    private boolean isOpeningBracket(String token) {
        return token.equals(OPEN_BRACKET);
    }

    private boolean isClosingBracket(String token) {
        return token.equals(CLOSE_BRACKET);
    }
}