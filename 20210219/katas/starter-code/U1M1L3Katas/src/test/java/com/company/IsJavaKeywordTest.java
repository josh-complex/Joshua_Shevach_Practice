package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class IsJavaKeywordTest {

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
    public void shouldTellUserIfInputIsAJavaKeywordWhenInputIsGiven() throws Exception {
        String[] args = null;
        String[] results =  {"not a java keyword", "is a java keyword", "is a java keyword"};

        int iteration = 0;

        while (iteration < results.length) {
            setIn("IsJavaKeyword", iteration);
            IsJavaKeyword.main(args);
            String actual = systemOutRule.getLog().toLowerCase();

            String failMessage = "Expected " + results[iteration];
            assertTrue(failMessage, actual.contains(results[iteration]));
            systemOutRule.clearLog();
            iteration++;
        }
    }
}

