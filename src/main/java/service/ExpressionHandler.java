package service;

import utils.Props;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class prepare and check expression
 */
public class ExpressionHandler {

    private final String SIGN_MINUS = Props.read("sign.minus");
    private final String SIGN_DEGREE = Props.read("sign.degree");
    private final String OPEN_BRACKET = Props.read("open.bracket");
    private final String CLOSE_BRACKET = Props.read("close.bracket");

    public String prepare(String expression) {

        if (expression == null || expression.trim().length() == 0) {
            throw new RuntimeException("Expression can't be empty!");
        }

        boolean startsWithNegativeNumber = expression.startsWith(SIGN_MINUS);

        checkWrongSpacesBetweenDigits(expression);

        expression = concat(OPEN_BRACKET, expression, CLOSE_BRACKET);
        expression = expression
                .replace(" ", "")
                .replace(concat(OPEN_BRACKET, SIGN_MINUS), concat(OPEN_BRACKET, "0", SIGN_MINUS))
                .replace(",", ".");

        expression = handleNegativeDegrees(expression, startsWithNegativeNumber);

        return expression;
    }

    private void checkWrongSpacesBetweenDigits(String expression) {

        Pattern pattern = Pattern.compile("\\d+\\s+\\d+");
        Matcher matcher = pattern.matcher(expression);

        String wrongInputs = "";
        while (matcher.find()) {
            wrongInputs = String.format("'%s' ", matcher.group());
        }

        if (!wrongInputs.trim().equals("")) {
            throw new IllegalArgumentException(
                    String.format("Don't use spaces between digits." +
                            " Wrong inputs: %s", wrongInputs.trim()));
        }
    }

    private String handleNegativeDegrees(String expression, boolean startsWithNegativeNumber) {

        if (!expression.contains(SIGN_DEGREE)) {
            return expression;
        }

        final String NEGATIVE_DEGREE_WITHOUT_BRACKETS = concat(SIGN_DEGREE, SIGN_MINUS);
        final String NEGATIVE_EXPRESSION_DEGREE_WITHOUT_BRACKETS = concat(SIGN_DEGREE, SIGN_MINUS, OPEN_BRACKET);

        String[] splittedExpression = expression.split("(?=\\" + SIGN_DEGREE + ")");

        if (startsWithNegativeNumber
                && !splittedExpression[0].contains(CLOSE_BRACKET)
                && splittedExpression[1].startsWith(SIGN_DEGREE)) {
            splittedExpression[0] = concat(OPEN_BRACKET, OPEN_BRACKET, splittedExpression[0].substring(1), CLOSE_BRACKET);
        }

        for (int i = 0; i < splittedExpression.length; i++) {

            if (splittedExpression[i].startsWith(NEGATIVE_EXPRESSION_DEGREE_WITHOUT_BRACKETS)) {
                int j = NEGATIVE_EXPRESSION_DEGREE_WITHOUT_BRACKETS.length();
                StringBuilder degreeValue = new StringBuilder();
                while (splittedExpression[i].charAt(j) != toChar(CLOSE_BRACKET)) {
                    degreeValue.append(splittedExpression[i].charAt(j++));
                }
                splittedExpression[i] = concat(SIGN_DEGREE, OPEN_BRACKET, "0", SIGN_MINUS,
                        OPEN_BRACKET, degreeValue.toString(), CLOSE_BRACKET, splittedExpression[i].substring(j));
            }

            if (splittedExpression[i].startsWith(NEGATIVE_DEGREE_WITHOUT_BRACKETS)) {
                int j = NEGATIVE_DEGREE_WITHOUT_BRACKETS.length();
                StringBuilder degreeValue = new StringBuilder();
                while (Character.isDigit(splittedExpression[i].charAt(j))) {
                    degreeValue.append(splittedExpression[i].charAt(j++));
                }
                splittedExpression[i] = concat(SIGN_DEGREE, OPEN_BRACKET, "0", SIGN_MINUS, degreeValue.toString(), CLOSE_BRACKET,
                        splittedExpression[i].substring(j));
            }

        }
        return String.join("", splittedExpression);
    }

    private String concat(String... strings) {

        StringBuilder result = new StringBuilder();
        for (String s : strings) {
            result.append(s);
        }
        return result.toString();
    }

    private char toChar(String str) {
        return str.charAt(0);
    }
}
