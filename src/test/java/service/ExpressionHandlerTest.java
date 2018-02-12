package service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ExpressionHandlerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ExpressionHandler expressionHandler;

    @Before
    public void setUp() throws Exception {
        expressionHandler = new ExpressionHandler();
    }

    @Test
    public void prepareTest1() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Expression can't be empty!"));

        String expression = "";

        expressionHandler.prepare(expression);
    }

    @Test
    public void prepareTest2() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Expression can't be empty!"));

        String expression = "   ";

        expressionHandler.prepare(expression);
    }

    @Test
    public void prepareTest3() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Expression can't be empty!"));

        String expression = null;

        expressionHandler.prepare(expression);
    }

    @Test
    public void prepareTest4() throws Exception {

        String expression = "1 + 2 * 3 ";
        String expected = "(1+2*3)";

        String actual = expressionHandler.prepare(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void prepareTest5() throws Exception {

        String expression = "1.0 + 2,2 * 3.4 ";
        String expected = "(1.0+2.2*3.4)";

        String actual = expressionHandler.prepare(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void prepareTest6() throws Exception {

        String expression = "1.0 + 2,2 * 3.4 ";
        String expected = "(1.0+2.2*3.4)";

        String actual = expressionHandler.prepare(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void prepareTest7() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Don't use spaces between digits. Wrong inputs: '1 2'"));

        String expression = "1 2 +3";

        expressionHandler.prepare(expression);
    }

    @Test
    public void prepareTest8() throws Exception {

        String expression = "-1^2";
        String expected = "((0-1)^2)";

        String actual = expressionHandler.prepare(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void prepareTest9() throws Exception {

        String expression = "-1";
        String expected = "(0-1)";

        String actual = expressionHandler.prepare(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void prepareTest10() throws Exception {

        String expression = "-1-(-1)";
        String expected = "(0-1-(0-1))";

        String actual = expressionHandler.prepare(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void prepareTest11() throws Exception {

        String expression = "10^-2";
        String expected = "(10^(0-2))";

        String actual = expressionHandler.prepare(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void prepareTest12() throws Exception {

        String expression = "10^-(2+3)";
        String expected = "(10^(0-(2+3)))";

        String actual = expressionHandler.prepare(expression);

        assertEquals(expected, actual);
    }





}