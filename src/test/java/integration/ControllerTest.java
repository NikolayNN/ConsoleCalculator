package integration;

import controller.Controller;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    private final String LINE_SEPARATOR = System.lineSeparator();

    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() throws Exception {
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void test1() throws Exception {
        //given
        in.add("2+2*2");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "result: 6" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test2() throws Exception {
        //given
        in.add("help");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "This is a cmd calculator. The calculator supports next operations " + LINE_SEPARATOR +
                "\tAddition: +" + LINE_SEPARATOR +
                "\tSubtraction: -" + LINE_SEPARATOR +
                "\tMultiplication: *" + LINE_SEPARATOR +
                "\tDivision: /" + LINE_SEPARATOR +
                "\tExponentiation ^" + LINE_SEPARATOR +
                "\tBrackets: ()" + LINE_SEPARATOR +
                "Precedence of operation:" + LINE_SEPARATOR +
                "\tBrackets >> Degree >> Multiplication/Division >> Addition/Subtraction" + LINE_SEPARATOR +
                "Accuracy of calculations: 15 decimal places. You can change the parameter in file app.properties" + LINE_SEPARATOR +
                "You can use '.' or/and ',' like delimiter of decimal places. You will get result always with '.'" + LINE_SEPARATOR +
                "" + LINE_SEPARATOR +
                "You can put spaces between numbers and operators, but you can't put spaces between digits." + LINE_SEPARATOR +
                "Available commands: " + LINE_SEPARATOR +
                "\texit - end the programm" + LINE_SEPARATOR +
                "\thelp - get the information" + LINE_SEPARATOR +
                "" + LINE_SEPARATOR +
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
                "\t(2^1 + 3^2)^-(1+1)" + LINE_SEPARATOR +
                "" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye."+ LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test3() throws Exception {
        //given
        in.add("2+2*2");
        in.add("help");
        in.add("3*3+3");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "result: 6" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "This is a cmd calculator. The calculator supports next operations " + LINE_SEPARATOR +
                "\tAddition: +" + LINE_SEPARATOR +
                "\tSubtraction: -" + LINE_SEPARATOR +
                "\tMultiplication: *" + LINE_SEPARATOR +
                "\tDivision: /" + LINE_SEPARATOR +
                "\tExponentiation ^" + LINE_SEPARATOR +
                "\tBrackets: ()" + LINE_SEPARATOR +
                "Precedence of operation:" + LINE_SEPARATOR +
                "\tBrackets >> Degree >> Multiplication/Division >> Addition/Subtraction" + LINE_SEPARATOR +
                "Accuracy of calculations: 15 decimal places. You can change the parameter in file app.properties" + LINE_SEPARATOR +
                "You can use '.' or/and ',' like delimiter of decimal places. You will get result always with '.'" + LINE_SEPARATOR +
                "" + LINE_SEPARATOR +
                "You can put spaces between numbers and operators, but you can't put spaces between digits." + LINE_SEPARATOR +
                "Available commands: " + LINE_SEPARATOR +
                "\texit - end the programm" + LINE_SEPARATOR +
                "\thelp - get the information" + LINE_SEPARATOR +
                "" + LINE_SEPARATOR +
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
                "\t(2^1 + 3^2)^-(1+1)" + LINE_SEPARATOR +
                "" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "result: 12" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye."+ LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test4() throws Exception {
        //given
        in.add("5 + 1/0");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Arithmetically invalid operation: / by zero" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test5() throws Exception {
        //given
        in.add("4 2 * 3");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Please, check your input. Don't use spaces between digits. Wrong inputs: '4 2'" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test6() throws Exception {
        //given
        in.add("4a * 5");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Please, check your input. The token is unacceptable: '4a'" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test7() throws Exception {
        //given
        in.add("4 + 5 + ((5+3)");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Please, check your input. Wrong count of opening and closing brackets. Number of open brackets > number of closing brackets" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test8() throws Exception {
        //given
        in.add("4 + 5 + (5+3))");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Please, check your input. Wrong count of opening and closing brackets. Number of open brackets < number of closing brackets" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test9() throws Exception {
        //given
        in.add("4.0 + 5,5 + 1");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "result: 10.5" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test10() throws Exception {
        //given
        in.add("");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Please, check your input. Expression can't be empty!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void test11() throws Exception {
        //given
        in.add("   ");
        in.add("exit");

        //when
        new Controller().run();

        //then
        String expected = "Hello!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Please, check your input. Expression can't be empty!" + LINE_SEPARATOR +
                "Input your expression or 'help' for info" + LINE_SEPARATOR +
                "Goodbye." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    public String getData() {
        String result;
        try {
            result = new String(out.toByteArray(), "UTF-8");
            out.reset();
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
        return result;
    }
}
