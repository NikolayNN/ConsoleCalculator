package service;

import utils.Properties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The class prepared and check expression
 */
public class ExpressionHandler {

    private final String SIGN_MINUS = Properties.read("sign.minus");
    private final String SIGN_DEGREE = Properties.read("sign.degree");
    private final static String OPEN_BRACKET = Properties.read("open.bracket");
    private final String CLOSE_BRACKET = Properties.read("close.bracket");
    private final String SEPARATOR = " ";

    public String prepare(String expression) {

        if (expression == null || expression.trim().equals("")) {
            throw new RuntimeException("Expression can't be empty!");
        }

        checkWrongSpacesBetweenDigits(expression);

        expression = concat(OPEN_BRACKET, expression, CLOSE_BRACKET);
        expression = expression
                .replace(SEPARATOR, "")
                .replace(concat(OPEN_BRACKET, SIGN_MINUS), concat(OPEN_BRACKET, "0", SIGN_MINUS))
                .replace(",","." );

        expression = handleNegativeDegrees(expression);

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

    private String handleNegativeDegrees(String expression) {

        if (!expression.contains(SIGN_DEGREE)) {
            return expression;
        }

        final String NEGATIVE_NUMBER_DEGREE_WITHOUT_BRACKETS = concat(SIGN_DEGREE, SIGN_MINUS);
        final String NEGATIVE_EXPRESSION_DEGREE_WITHOUT_BRACKETS = concat(SIGN_DEGREE,  SIGN_MINUS, OPEN_BRACKET);

        String[] splitted = expression.split("(?=\\" + SIGN_DEGREE + ")");
        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].startsWith(NEGATIVE_EXPRESSION_DEGREE_WITHOUT_BRACKETS)) {
                int j = NEGATIVE_EXPRESSION_DEGREE_WITHOUT_BRACKETS.length();
                StringBuilder degreeValue = new StringBuilder();
                while (splitted[i].charAt(j) != toChar(CLOSE_BRACKET)) {
                    degreeValue.append(splitted[i].charAt(j++));
                }
                //^(0-%s)%s
                splitted[i] = concat(SIGN_DEGREE, OPEN_BRACKET, "0", SIGN_MINUS,
                        OPEN_BRACKET, degreeValue.toString(), CLOSE_BRACKET, splitted[i].substring(j));
            }
            if (splitted[i].startsWith(NEGATIVE_NUMBER_DEGREE_WITHOUT_BRACKETS)) {
                int j = NEGATIVE_NUMBER_DEGREE_WITHOUT_BRACKETS.length();
                StringBuilder degreeValue = new StringBuilder();
                while (Character.isDigit(splitted[i].charAt(j))) {
                    degreeValue.append(splitted[i].charAt(j++));
                }
                //^(0-%s)%s
                splitted[i] = concat(SIGN_DEGREE, OPEN_BRACKET, "0", SIGN_MINUS, degreeValue.toString(), CLOSE_BRACKET,
                        splitted[i].substring(j));
            }
        }
        return String.join("", splitted);
    }

    private String concat(String ... strings ){

        StringBuilder result = new StringBuilder();
        for (String s : strings) {
            result.append(s);
        }
        return result.toString();
    }

    private char toChar(String str){
        return str.charAt(0);
    }
}
