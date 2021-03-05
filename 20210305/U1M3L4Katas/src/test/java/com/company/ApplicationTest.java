package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class ApplicationTest {

    private InputStream original;

    private static void setIn(int iteration) throws FileNotFoundException  {
        final FileInputStream fips = new FileInputStream(new File("testfiles/input" + iteration + ".txt"));
        System.setIn(fips);
    }

    @Before
    public void setUp() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("pets.txt", true));
        out.println("0: Dog");
        out.flush();
        out.close();

        systemOutRule.clearLog();
        original = System.in;
    }

    @After
    public void resetIn() {
        System.setIn(original);
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void shouldCatchNumberFormatException() throws Exception {
        String[] args = null;
        setIn(1);

        Application.main(args);
        String output = systemOutRule.getLog();

        String failMessage = "Should display the specified message when given a non-integer input";
        assertTrue(failMessage, output.contains("Please enter a valid number"));
        failMessage = "Should tell user to have a nice day";
        assertTrue(failMessage, output.contains("Have a nice day"));
    }

    @Test
    public void shouldCatchArrayIndexOutOfBounds() throws Exception {
        String[] args = null;
        setIn(2);

        Application.main(args);
        String output = systemOutRule.getLog();

        String failMessage = "Should display the specified message when given an out of bounds index";
        assertTrue(failMessage, output.contains("Please choose an available pet"));
        failMessage = "Should tell user to have a nice day";
        assertTrue(failMessage, output.contains("Have a nice day"));
    }

    @Test
    public void fileNotFoundTest() throws FileNotFoundException{
        File pets = new File("pets.txt");
        pets.delete();
        String[] args = null;
        setIn(3);

        Application.main(args);
        String output = systemOutRule.getLog();

        String failMessage = "Should display the specified message when given an out of bounds index";
        assertTrue(failMessage, output.contains("The following file does not seem to exist:"));
        failMessage = "Should tell user to have a nice day";
        assertTrue(failMessage, output.contains("Have a nice day"));
    }
}
