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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class AddFiveTest {

    private InputStream original;

    private static void setIn(String dir, int iteration) throws FileNotFoundException  {
        final FileInputStream fips = new FileInputStream(new File("testfiles/" + dir + "/input" + iteration + ".txt"));
        System.setIn(fips);
    }

    @Before
    public void saveOriginal() {
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
    public void shouldOutputSumOfFiveNumbersWhenGivenFiveNumbers() throws Exception {
        String[] results = {"75", "191", "104"};
        String[] args = null;

        int iteration = 0;

        while (iteration < results.length) {

            // set input to file stream
            setIn("MultipleInputs", iteration);

            // run main method and capture output
            AddFive.main(args);
            String output = systemOutRule.getLog();

            // make sure none of the results appear except the one we're checking
            for(int i = 1; i < results.length; i++) {
                String next = results[(iteration + i) % results.length];
                assertFalse("\nExpected output to not contain " + next, output.contains(next));
            }

            // check that the current iteration output contains intended result
            String message = "\nExpected output to contain " + results[iteration] + "\n";
            assertTrue(message, output.contains(results[iteration]));

            // clear log and increment iterator
            systemOutRule.clearLog();
            iteration++;
        }
    }
}

