package controller;

import service.Calculator;
import service.ExpressionHandler;
import view.Console;
import view.View;

import java.math.BigDecimal;

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
            }else{
                try {
                    BigDecimal result = calculator.calc(expression);
                    view.write(String.format("result: %s", result.toString()));
                }catch (Exception ex){
                    view.write(ex.getMessage());
                }
            }
        }

        view.write("Goodbye.");

    }
}
