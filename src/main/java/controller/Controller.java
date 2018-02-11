package controller;

import service.Calculator;
import service.ExpressionHandler;
import view.Console;
import view.View;

public class Controller {

    public void run() {
        ExpressionHandler expressionHandler = new ExpressionHandler();
        Calculator calculator = new Calculator(expressionHandler);
        View view = new Console();

        view.write("Hello!");
        while (true) {
            view.write("Input your expression or 'stop' for exit");
            String expression = view.read();
            if (expression.equalsIgnoreCase("stop")) {
                break;
            } else {
                try {
                    String result = calculator.calc(expression);
                    view.write(String.format("result: %s", result));
                } catch (ArithmeticException ex) {
                    view.write(ex.getMessage());
                } catch (Exception ex) {
                    view.write("Please, check your input. " + ex.getMessage());
                }
            }
        }

        view.write("Goodbye.");
    }
}
