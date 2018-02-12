package controller.commands.impl;

import controller.commands.Command;
import service.Calculator;
import service.ExpressionHandler;

public class ExpressionCommand extends Command {

    private ExpressionHandler expressionHandler;
    private Calculator calculator;

    public ExpressionCommand(ExpressionHandler expressionHandler, Calculator calculator) {
        this.expressionHandler = expressionHandler;
        this.calculator = calculator;
    }

    @Override
    public String perform() {
        String preparedExpression = expressionHandler.prepare(rawCommand);
        String result;
        try {
            result = calculator.calc(preparedExpression);
        } catch (ArithmeticException ex) {
            return String.format("Arithmetically invalid operation: %s", ex.getMessage());
        }
        return "result: " + result;
    }
}
