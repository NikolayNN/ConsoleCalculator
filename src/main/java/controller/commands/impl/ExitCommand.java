package controller.commands.impl;

import controller.commands.Command;
import exception.CalculatorExitException;

public class ExitCommand extends Command {

    @Override
    public String perform() {
        throw new CalculatorExitException("exit");
    }
}
