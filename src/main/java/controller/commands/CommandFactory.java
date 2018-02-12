package controller.commands;

import controller.commands.impl.ExitCommand;
import controller.commands.impl.ExpressionCommand;
import controller.commands.impl.HelpCommand;
import service.Calculator;
import service.ExpressionHandler;

public class CommandFactory {

    private final String EXIT_COMMAND = "exit";
    private final String HELP_COMMAND = "help";

    private Calculator calculator;
    private ExpressionHandler expressionHandler;

    public CommandFactory(Calculator calculator, ExpressionHandler expressionHandler) {
        this.calculator = calculator;
        this.expressionHandler = expressionHandler;
    }

    public Command createCommand(String rawCommand) {
        if (rawCommand.equalsIgnoreCase(HELP_COMMAND)) {
            return new HelpCommand();
        }
        if (rawCommand.equalsIgnoreCase(EXIT_COMMAND)) {
            return new ExitCommand();
        }
        Command expressionCommand = new ExpressionCommand(expressionHandler, calculator);
        expressionCommand.setup(rawCommand);
        return expressionCommand;
    }
}
