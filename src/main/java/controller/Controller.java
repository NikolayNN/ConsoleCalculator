package controller;

import controller.commands.Command;
import controller.commands.CommandFactory;
import exception.CalculatorExitException;
import service.Calculator;
import service.ExpressionHandler;
import view.Console;
import view.View;

public class Controller {

    public void run() {
        ExpressionHandler expressionHandler = new ExpressionHandler();
        Calculator calculator = new Calculator(expressionHandler);
        View view = new Console();
        CommandFactory commandFactory = new CommandFactory(calculator, expressionHandler);

        view.write("Hello!");
        while (true) {
            view.write("Input your expression or 'help' for info");
            try {
                Command command = commandFactory.createCommand(view.read());
                view.write(command.perform());
            }catch (CalculatorExitException ex){
                break;
            }
            catch (Exception ex) {
                view.write("Please, check your input. " + ex.getMessage());
            }
        }
        view.write("Goodbye.");
    }
}
