import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.Calculator;
import service.ExpressionHandler;

import java.math.BigDecimal;

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
        BigDecimal expected = new BigDecimal("6");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc2() throws Exception {

        String expression = "2*2+2";
        BigDecimal expected = new BigDecimal(6);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc3() throws Exception {

        String expression = "1+2+3";
        BigDecimal expected = new BigDecimal(6);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc4() throws Exception {

        String expression = "1-2-3";
        BigDecimal expected = new BigDecimal(-4);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc5() throws Exception {

        String expression = "1+2-3";
        BigDecimal expected = new BigDecimal( 0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc6() throws Exception {

        String expression = "0-2+10";
        BigDecimal expected = new BigDecimal(8);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc7() throws Exception {

        String expression = "1 + 2  + 3- 4- 5 -6 +8  +9+10";
        BigDecimal expected = new BigDecimal(18.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc8() throws Exception {

        String expression = "-2-6+3";
        BigDecimal expected = new BigDecimal(-5);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc9() throws Exception {

        String expression = "(1+2)-(3+4)";
        BigDecimal expected = new BigDecimal(-4);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc10() throws Exception {

        String expression = "1+(-1)";
        BigDecimal expected = new BigDecimal(0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc11() throws Exception {

        String expression = "1-(-1)";
        BigDecimal expected = new BigDecimal(2);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc12() throws Exception {

        String expression = "((1+2)-(3+4))-(5+6)";
        BigDecimal expected = new BigDecimal(-15);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc13() throws Exception {

        String expression = "((1+2)-(3+4))-((5+6)+(1+9))+(-1)";
        BigDecimal expected = new BigDecimal(-26);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc14() throws Exception {

        String expression = "(-1)*(-1)";
        BigDecimal expected = new BigDecimal(1);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc15() throws Exception {

        String expression = "(-1)*1";
        BigDecimal expected = new BigDecimal(-1);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc16() throws Exception {

        String expression = "2*8+3*6";
        BigDecimal expected = new BigDecimal(34);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc17() throws Exception {

        String expression = "2*(8+3)*6";
        BigDecimal expected = new BigDecimal(132);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc18() throws Exception {

        String expression = "-1*1";
        BigDecimal expected = new BigDecimal(-1);

        BigDecimal actual = calculator.calc(expression);

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
        BigDecimal expected = new BigDecimal(2);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc23() throws Exception {

        String expression = "2/(1+3)*2";
        BigDecimal expected = new BigDecimal("1.0");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc24() throws Exception {

        String expression = "2*6/(1+3)*(2+3)";
        BigDecimal expected = new BigDecimal(15.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc25() throws Exception {

        String expression = "2*6/(1+3)*(2+3)";
        BigDecimal expected = new BigDecimal(15.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc26() throws Exception {

        String expression = "2 ^ 3";
        BigDecimal expected = new BigDecimal(8.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc27() throws Exception {

        String expression = "5*2 ^ 3";
        BigDecimal expected = new BigDecimal(40.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc28() throws Exception {

        String expression = "100 ^ 0";
        BigDecimal expected = new BigDecimal(1.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc29() throws Exception {

        String expression = "2^(1+2)";
        BigDecimal expected = new BigDecimal(8.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc30() throws Exception {

        String expression = "2 ^(1+2)";
        BigDecimal expected = new BigDecimal(8.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc31() throws Exception {

        String expression = "2 ^ (1+2)";
        BigDecimal expected = new BigDecimal(8.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc32() throws Exception {

        String expression = "(17 ^ 4 + 5 * 974 ^ 33 + 2.24 * 4.75)^0";
        BigDecimal expected = new BigDecimal(1.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc33() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Please, don't use spaces between digits. " +
                "Wrong inputs: '1 0'"));

        String expression = "1 0 +18";

        calculator.calc(expression);
    }

    @Test
    public void calc34() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Please, don't use spaces between digits. " +
                "Wrong inputs: '12345 785236'"));

        String expression = "12345 785236 + 18";

        calculator.calc(expression);
    }

    @Test
    public void calc35() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Please, don't use spaces between digits. " +
                "Wrong inputs: '12345 785236' '98756 85632'"));

        String expression = "12345 785236 + 18 * 98756 85632";

        calculator.calc(expression);
    }

    @Test
    public void calc36() throws Exception {

        String expression = "10^-1";
        BigDecimal expected = new BigDecimal("0.1");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc37() throws Exception {

        String expression = "10  ^   -   1   ";
        BigDecimal expected = new BigDecimal("0.1");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc38() throws Exception {

        String expression = "10^-1+20";
        BigDecimal expected = new BigDecimal("20.1");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc39() throws Exception {

        String expression = "  10  ^  -  1  +  20";
        BigDecimal expected = new BigDecimal("20.1");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc40() throws Exception {

        String expression = "10^-0";
        BigDecimal expected = new BigDecimal(1.0);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc41() throws Exception {

        String expression = "10^-(1+1)";
        BigDecimal expected = new BigDecimal("0.01");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc42() throws Exception {

        String expression = "10  ^  -  (1+1)";
        BigDecimal expected = new BigDecimal("0.01");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc43() throws Exception {

        String expression = "10^-(1+1)*1000";
        BigDecimal expected = new BigDecimal("10.00");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc44() throws Exception {

        String expression = "(4 + 3) * 2 ^ -2";
        BigDecimal expected = new BigDecimal(1.75);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc45() throws Exception {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Please check your input. The token is unacceptable: '3a'"));

        String expression = "(4 + 3a) * 2 ^ -2";

        BigDecimal actual = calculator.calc(expression);
    }

    @Test
    public void calc46() throws Exception {

        String expression = "10^(-1)";
        BigDecimal expected = new BigDecimal("0.1");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc47() throws Exception {

        String expression = "10^(-(5-6)";
        BigDecimal expected = new BigDecimal(10);

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc48() throws Exception {

        String expression = "1.0 + 2.0 + 3.0";
        BigDecimal expected = new BigDecimal("6.0");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc49() throws Exception {

        String expression = "1.0 + 2,0 + 3.0";
        BigDecimal expected = new BigDecimal("6.0");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void calc50() throws Exception {

        String expression = "1,0 + 2,0 + 3,0";
        BigDecimal expected = new BigDecimal("6.0");

        BigDecimal actual = calculator.calc(expression);

        assertEquals(expected, actual);
    }
}