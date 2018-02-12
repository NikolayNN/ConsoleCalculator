package controller.commands.impl;

import controller.commands.Command;
import utils.Props;

public class HelpCommand extends Command {

    private final String LINE_SEPARATOR = System.lineSeparator();

    private final String SIGN_PLUS = Props.read("sign.plus");
    private final String SIGN_MINUS = Props.read("sign.minus");
    private final String SIGN_MULTIPLICATION = Props.read("sign.multiplication");
    private final String SIGN_DIVISION = Props.read("sign.division");
    private final String SIGN_DEGREE = Props.read("sign.degree");
    private final String OPEN_BRACKET = Props.read("open.bracket");
    private final String CLOSE_BRACKET = Props.read("close.bracket");

    private final int ACCURACY = Props.readInt("accuracy");

    @Override
    public String perform() {

        String result = "This is a cmd calculator. The calculator supports next operations " + LINE_SEPARATOR +
                    "\tAddition: " + SIGN_PLUS + LINE_SEPARATOR +
                    "\tSubtraction: " + SIGN_MINUS + LINE_SEPARATOR +
                    "\tMultiplication: " + SIGN_MULTIPLICATION + LINE_SEPARATOR +
                    "\tDivision: " + SIGN_DIVISION + LINE_SEPARATOR +
                    "\tExponentiation " + SIGN_DEGREE + LINE_SEPARATOR +
                    "\tBrackets: " + OPEN_BRACKET + CLOSE_BRACKET + LINE_SEPARATOR +
                "Precedence of operation:" + LINE_SEPARATOR +
                    "\tBrackets >> Degree >> Multiplication/Division >> Addition/Subtraction" + LINE_SEPARATOR +
                "Accuracy of calculations: " + ACCURACY + " decimal places. You can change the parameter in file app.properties" + LINE_SEPARATOR +
                "You can use '.' or/and ',' like delimiter of decimal places. You will get result always with '.'" + LINE_SEPARATOR + LINE_SEPARATOR +
                "You can put spaces between numbers and operators, but you can't put spaces between digits." + LINE_SEPARATOR +
                "Available commands: " + LINE_SEPARATOR +
                    "\texit - end the programm" + LINE_SEPARATOR +
                    "\thelp - get the information" + LINE_SEPARATOR + LINE_SEPARATOR +
                "How to work with the program: " + LINE_SEPARATOR +
                    "\tAfter launch the program you should input expression and press enter." + LINE_SEPARATOR +
                    "\tIf calculation was successful you wil get result" + LINE_SEPARATOR +
                    "\tElse you will get error message." + LINE_SEPARATOR +
                "Some examples of expressions:" + LINE_SEPARATOR +
                    "\t2+2*2" + LINE_SEPARATOR +
                    "\t(10 + 5)/15" + LINE_SEPARATOR +
                    "\t(10.0 + 5,6)/15" + LINE_SEPARATOR +
                    "\t(10.0^2 + 5,6)/10" + LINE_SEPARATOR +
                    "\t(2^1 + 3^2)^-1" + LINE_SEPARATOR +
                    "\t(2^1 + 3^2)^-(1+1)" + LINE_SEPARATOR;

        return result;
    }
}
