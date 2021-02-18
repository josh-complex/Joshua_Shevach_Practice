package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;


public class HiLoTest {

    private InputStream original;


    private static void setIn(String dir, int iteration) throws FileNotFoundException  {
        final FileInputStream fips = new FileInputStream(new File("testfiles/" + dir + "/input" + iteration + ".txt"));
        System.setIn(fips);
    }

    @Before
    public void saveIn() {
        original = System.in;
    }

    @After
    public void resetIn() {
        System.setIn(original);
    }


    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void shouldFollowRulesOfHiLo() throws Exception {
        String[] args = null;

        String[] names = {"Mike", "Octavia", "Veronica"};

        int iteration = 0;

        while( iteration < 3 ) {
            setIn("inputs", iteration);
            HiLo.main(args);
            String actual = systemOutRule.getLog();


            String failMessage =  "Expected to see the welcome message \"Welcome to Hi-Low!\"";
            assertTrue(failMessage, actual.contains("Welcome to Hi-Low!"));

            String expected = "OK, " + names[iteration] + ", here are the rules:";
            failMessage =  "Expected \"OK, <name of user>, here are the rules:\"";
            assertTrue(failMessage, actual.contains(expected));

            failMessage = "Expected repeated prompts until the number was found";

            String[] split = actual.split(System.lineSeparator());
            String[] inputPrompts = Arrays.copyOfRange(split, 4, split.length);
            assertTrue(failMessage, inputPrompts.length > 2);

            expected = "Congratulations, " + names[iteration] + "! You win!";
            failMessage =  "Expected win message: \"Congratulations, [name of user]! You win!\"";
            assertTrue(failMessage, actual.contains(expected));

            failMessage =  "Expected final output: \"It took you [number of guesses] guesses to find my number!\"";
            assertTrue(failMessage, actual.contains("It took you "));
            assertTrue(failMessage, actual.contains(" guesses to find my number!"));

            systemOutRule.clearLog();
            iteration++;

        }
    }
}
