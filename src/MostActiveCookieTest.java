import java.util.*;
import java.io.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MostActiveCookieTest {
    MostActiveCookie mac;

    @BeforeEach
    void setUp() {
        mac = new MostActiveCookie();

        try {
            mac.readFile("/Users/amitbhandal/Downloads/mostCookies/data/cookie_log.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMostActiveCookiesEmptyOrInvalidTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        mac.getMostActiveCookies("2021-12-08");
        assertEquals(outContent.toString(), "Invalid date or date not found\n");
    }

    @Test
    void getMostActiveCookiesTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        mac.cookies = new ArrayList<String>(Arrays.asList("test0 2018-12-09T14:19:00+00:00",
                "test1 2018-12-09T14:20:00+00:00",
                "test2 2018-12-09T15:19:00+00:00",
                "test3 2018-12-09T15:55:00+00:00"));
        mac.getMostActiveCookies("2018-12-09");
        assertEquals("test3\ntest2\ntest1\ntest0\n", outContent.toString());
    }

    @Test
    void invalidFileInputTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            mac.readFile("null");
        } catch (IOException e) {
            assertTrue(true);
            assertEquals( "file not found\n", outContent.toString());
        }
    }
}