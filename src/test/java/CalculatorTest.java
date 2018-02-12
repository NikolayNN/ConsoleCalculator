import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.Calculator;
import service.ExpressionHandler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator(new ExpressionHandler());
    }

    @Test
    public void calc() throws Exception {

        String expression = "2+2*2";
        String expected = "6";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc2() throws Exception {

        String expression = "2*2+2";
        String expected = "6";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc3() throws Exception {

        String expression = "1+2+3";
        String expected = "6";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc4() throws Exception {

        String expression = "1-2-3";
        String expected = "-4";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc5() throws Exception {

        String expression = "1+2-3";
        String expected =  "0";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc6() throws Exception {

        String expression = "0-2+10";
        String expected = "8";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc7() throws Exception {

        String expression = "1 + 2  + 3- 4- 5 -6 +8  +9+10";
        String expected = "18";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc8() throws Exception {

        String expression = "-2-6+3";
        String expected = "-5";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc9() throws Exception {

        String expression = "(1+2)-(3+4)";
        String expected = "-4";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc10() throws Exception {

        String expression = "1+(-1)";
        String expected = "0";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc11() throws Exception {

        String expression = "1-(-1)";
        String expected = "2";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc12() throws Exception {

        String expression = "((1+2)-(3+4))-(5+6)";
        String expected = "-15";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc13() throws Exception {

        String expression = "((1+2)-(3+4))-((5+6)+(1+9))+(-1)";
        String expected = "-26";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc14() throws Exception {

        String expression = "(-1)*(-1)";
        String expected = "1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc15() throws Exception {

        String expression = "(-1)*1";
        String expected = "-1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc16() throws Exception {

        String expression = "2*8+3*6";
        String expected = "34";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc17() throws Exception {

        String expression = "2*(8+3)*6";
        String expected = "132";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc18() throws Exception {

        String expression = "-1*1";
        String expected = "-1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc19() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Expression can't be empty!"));

        String expression = "";

        calculator.calc(expression);
    }

    @Test
    public void calc20() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Expression can't be empty!"));

        String expression = "   ";

        calculator.calc(expression);
    }

    @Test
    public void calc21() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Expression can't be empty!"));

        String expression = null;

        calculator.calc(expression);
    }

    @Test
    public void calc22() throws Exception {

        String expression = "2/2*2";
        String expected = "2";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc23() throws Exception {

        String expression = "2/(1+3)*2";
        String expected = "1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc24() throws Exception {

        String expression = "2*6/(1+3)*(2+3)";
        String expected = "15";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc25() throws Exception {

        String expression = "2*6/(1+3)*(2+3)";
        String expected = "15";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc26() throws Exception {

        String expression = "2 ^ 3";
        String expected = "8";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc27() throws Exception {

        String expression = "5*2 ^ 3";
        String expected = "40";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc28() throws Exception {

        String expression = "100 ^ 0";
        String expected = "1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc29() throws Exception {

        String expression = "2^(1+2)";
        String expected = "8";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc30() throws Exception {

        String expression = "2 ^(1+2)";
        String expected = "8";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc31() throws Exception {

        String expression = "2 ^ (1+2)";
        String expected = "8";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc32() throws Exception {

        String expression = "(17 ^ 4 + 5 * 974 ^ 33 + 2.24 * 4.75)^0";
        String expected = "1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc33() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Don't use spaces between digits. " +
                "Wrong inputs: '1 0'"));

        String expression = "1 0 +18";

        calculator.calc(expression);
    }

    @Test
    public void calc34() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Don't use spaces between digits. " +
                "Wrong inputs: '12345 785236'"));

        String expression = "12345 785236 + 18";

        calculator.calc(expression);
    }

    @Test
    public void calc35() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Don't use spaces between digits. Wrong inputs: '98756 85632'"));

        String expression = "12345 785236 + 18 * 98756 85632";

        calculator.calc(expression);
    }

    @Test
    public void calc36() throws Exception {

        String expression = "10^-1";
        String expected = "0.1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc37() throws Exception {

        String expression = "10  ^   -   1   ";
        String expected = "0.1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc38() throws Exception {

        String expression = "10^-1+20";
        String expected = "20.1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc39() throws Exception {

        String expression = "  10  ^  -  1  +  20";
        String expected = "20.1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc40() throws Exception {

        String expression = "10^-0";
        String expected = "1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc41() throws Exception {

        String expression = "10^-(1+1)";
        String expected = "0.01";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc42() throws Exception {

        String expression = "10  ^  -  (1+1)";
        String expected = "0.01";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc43() throws Exception {

        String expression = "10^-(1+1)*1000";
        String expected = "10";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc44() throws Exception {

        String expression = "(4 + 3) * 2 ^ -2";
        String expected = "1.75";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc45() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("The token is unacceptable: '3a'"));

        String expression = "(4 + 3a) * 2 ^ -2";

        calculator.calc(expression);
    }

    @Test
    public void calc46() throws Exception {

        String expression = "10^(-1)";
        String expected = "0.1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc48() throws Exception {

        String expression = "1.0 + 2.0 + 3.0";
        String expected = "6";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc49() throws Exception {

        String expression = "1.0 + 2,0 + 3.0";
        String expected = "6";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc555() throws Exception {

        String expression = "1.0 + 2,0 + 3.0";
        String expected = "6";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc50() throws Exception {

        String expression = "1,0 + 2,0 + 3,0";
        String expected = "6";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc51() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Invalid expression."));

        String expression = "*5*5";

        calculator.calc(expression);
    }

    @Test
    public void calc51a1() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Invalid expression."));

        String expression = "5+*5";

        calculator.calc(expression);
    }

    @Test
    public void calc51a2() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Invalid expression."));

        String expression = "5+5*";

        calculator.calc(expression);
    }

    @Test
    public void calc51a3() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Invalid expression."));

        String expression = "-+(5+5)";

        calculator.calc(expression);
    }

    @Test
    public void calc51a4() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Invalid expression."));

        String expression = "(5*-5)";

        calculator.calc(expression);
    }

    @Test
    public void calc51a5() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Invalid expression."));

        String expression = "^(5-5)";

        calculator.calc(expression);
    }

    @Test
    public void calc51a6() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Invalid expression."));

        String expression = "(5-5)^";

        calculator.calc(expression);
    }


    @Test
    public void calc52() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Wrong count of opening and closing brackets. " +
                "Number of open brackets > number of closing brackets"));

        String expression = "(5+5";

        calculator.calc(expression);
    }

    @Test
    public void calc53() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Wrong count of opening and closing brackets. " +
                "Number of open brackets < number of closing brackets"));

        String expression = "5+5)";

        calculator.calc(expression);
    }

    @Test
    public void calc54() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Wrong count of opening and closing brackets. " +
                "Number of open brackets > number of closing brackets"));

        String expression = "(5+5)(";

        calculator.calc(expression);
    }

    @Test
    public void calc55() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Wrong count of opening and closing brackets. " +
                "Number of open brackets > number of closing brackets"));

        String expression = "(5+5)(5";

        calculator.calc(expression);
    }

    @Test
    public void calc56() throws Exception {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(is("Wrong count of opening and closing brackets." +
                " Number of open brackets < number of closing brackets"));

        String expression = "(5+5))5";

        calculator.calc(expression);
    }

    @Test
    public void calc57() throws Exception {

        expectedException.expect(ArithmeticException.class);
        expectedException.expectMessage(is("/ by zero"));

        String expression = "5/0";

        calculator.calc(expression);
    }

    @Test
    public void calc58() throws Exception {

        String expression = "1,85 + 2,38 + 3,91";
        String expected = "8.14";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc59() throws Exception {

        String expression = "1.85/3,91";
        String expected = "0.473145780051151";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc60() throws Exception {

        String expression = "1000*2000";
        String expected = "2000000";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc61() throws Exception {

        String expression = "123^10*213";
        String expected = "168822651845905283976237";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc62() throws Exception {

        String expression = "123^10*213/13";
        String expected = "12986357834300406459710.538461538461538";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc63() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("The token is unacceptable: 'aaaaaa'"));

        String expression = "aaaaaa";

        calculator.calc(expression);
    }


    @Test
    public void calc64() throws Exception {

        String expression = "123";
        String expected = "123";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc65() throws Exception {

        String expression = "(123)";
        String expected = "123";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc66() throws Exception {

        String expression = "(-123)";
        String expected = "-123";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc67() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Invalid expression."));

        String expression = "+";

       calculator.calc(expression);
    }

    @Test
    public void calc68() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Invalid expression."));

        String expression = "((+-";

        calculator.calc(expression);
    }

    @Test
    public void calc69() throws Exception {

        String expression = "-(10 + 9)^2";
        String expected = "-361";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc70() throws Exception {

        String expression = "0-1^2";
        String expected = "-1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc71() throws Exception {

        String expression = "-1^2";
        String expected = "1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc72() throws Exception {

        String expression = "-(0-1)^2";
        String expected = "-1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc73() throws Exception {

        String expression = "-(-(0-1))^2*2*(-1)";
        String expected = "2";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc74() throws Exception {

        String expression = "10^2-(6*4+1)*5+5*3^2";
        String expected = "20";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc75() throws Exception {

        String expression = "10-(6+1)+5*5^2";
        String expected = "128";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc76() throws Exception {

        String expression = "10-(6*4+1)*5+5*3^2";
        String expected = "-70";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc77() throws Exception {

        String expression = "10-(6+1)*5+5*3^2";
        String expected = "20";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc78() throws Exception {

        String expression = "10-(6+1)+5*3^2";
        String expected = "48";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc79() throws Exception {

        String expression = "10-(6+1)*5+5";
        String expected = "-20";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc80() throws Exception {

        String expression = "10-5*(6/2+1)+5";
        String expected = "-5";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc81() throws Exception {

        String expression = "10-(6/2+1)+5";
        String expected = "11";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc83() throws Exception {

        String expression = "10-(6/2+1)*5+5";
        String expected = "-5";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc84() throws Exception {

        String expression = "2 + 1 - 6 / (1 + 2)";
        String expected = "1";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc85() throws Exception {

        String expression = "2 + 2 * 3 - 4";
        String expected = "4";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc86() throws Exception {

        String expression = "6^2/2+4";
        String expected = "22";

        String actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

}