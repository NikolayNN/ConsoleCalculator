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
    public void start() throws Exception {
        //given
        in.add("2+2*2");
        in.add("stop");

        //when
        new Controller().run();

        //then
        String expected = "";

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
