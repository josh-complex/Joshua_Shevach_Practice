package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class YourAgeCanTest {

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
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void shouldPrintExpectedActionWhenAgeIsGiven() throws Exception {
        String[] args = null;
        String[] results = {"vote", "alcohol", "president", "aarp", "retire", "octogenarian", "century"};

        String newLine = System.lineSeparator();

        int iterations = 0;

        setIn("YourAgeCan", iterations);
        YourAgeCan.main(args);
        String[] lines = systemOutRule.getLog().split(newLine);
        String failMessage = "Expected no output if age is less than 18";
        assertEquals(lines.length, 1);
        iterations++;
        systemOutRule.clearLog();

        while (iterations < 8) {
            setIn("YourAgeCan", iterations);
            YourAgeCan.main(args);
            String actual = systemOutRule.getLog().toLowerCase();
            failMessage = "Expected all outputs that the age qualified for.";
            if (iterations < 6) {
                for(int i = 0; i < iterations; i++) {
                    assertTrue(failMessage, actual.contains(results[i]));
                }
            } else {
                for(int i = 0; i < 5; i++) {
                    assertTrue(failMessage, actual.contains(results[i]));
                }

                if (iterations == 7) {
                    assertTrue(failMessage, actual.contains(results[6]));
                }
            }

            systemOutRule.clearLog();
            iterations++;
        }

    }
}


