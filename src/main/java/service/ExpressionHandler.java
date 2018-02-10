package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The class prepared and check expression
 */
public class ExpressionHandler {

    private final String SIGN_DEGREE = "^";
    private final String OPEN_BRACKET = "(";
    private final String CLOSE_BRACKET = ")";
    private final String SEPARATOR = " ";

    public String prepare(String expression) {

        if (expression == null || expression.trim().equals("")) {
            throw new RuntimeException("Expression can't be empty!");
        }

        checkWrongSpacesBetweenDigits(expression);

        expression = OPEN_BRACKET + expression + CLOSE_BRACKET;
        expression = expression
                .replace(SEPARATOR, "")
                .replace("(-", "(0-")
                .replace(",","." );

        expression = handleNegativeDegrees(expression);

        return expression;
    }

    private void checkWrongSpacesBetweenDigits(String expression) {

        Pattern pattern = Pattern.compile("\\d+\\s+\\d+");
        Matcher matcher = pattern.matcher(expression);
        StringBuilder wrongInputs = new StringBuilder();

        while (matcher.find()) {
            wrongInputs
                    .append("'").append(matcher.group()).append("'")
                    .append(" ");
        }

        if (!wrongInputs.toString().trim().equals("")) {
            throw new IllegalArgumentException(
                    String.format("Please, don't use spaces between digits." +
                            " Wrong inputs: %s", wrongInputs.toString().trim()));
        }
    }

    private String handleNegativeDegrees(String expression) {

        if (!expression.contains(SIGN_DEGREE)) {
            return expression;
        }

        final String NEGATIVE_NUMBER_DEGREE_WITHOUT_BRACKETS = "^-";
        final String NEGATIVE_EXPRESSION_DEGREE_WITHOUT_BRACKETS = "^-(";

        String[] splitted = expression.split("(?=\\^)");
        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].startsWith(NEGATIVE_EXPRESSION_DEGREE_WITHOUT_BRACKETS)) {
                int j = NEGATIVE_EXPRESSION_DEGREE_WITHOUT_BRACKETS.length();
                StringBuilder degreeValue = new StringBuilder();
                while (splitted[i].charAt(j) != ')') {
                    degreeValue.append(splitted[i].charAt(j++));
                }
                splitted[i] = String.format("^(0-(%s)%s", degreeValue.toString(), splitted[i].substring(j));
            }
            if (splitted[i].startsWith(NEGATIVE_NUMBER_DEGREE_WITHOUT_BRACKETS)) {
                int j = NEGATIVE_NUMBER_DEGREE_WITHOUT_BRACKETS.length();
                StringBuilder degreeValue = new StringBuilder();
                while (Character.isDigit(splitted[i].charAt(j))) {
                    degreeValue.append(splitted[i].charAt(j++));
                }
                splitted[i] = String.format("^(0-%s)%s", degreeValue.toString(), splitted[i].substring(j));
            }
        }
        return String.join("", splitted);
    }
}
