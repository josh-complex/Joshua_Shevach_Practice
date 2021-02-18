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

import static org.junit.Assert.assertEquals;

public class FavoriteProgrammingLanguageTest {

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
    public void shouldAskForUserInputUntilInputEqualsJavaWhenInputIsGiven() throws Exception {
        String[] args = null;
        String expected = "That's what I was looking for! Java is definitely the answer!";

        setIn("FavoriteProgrammingLanguage", 0);
        String newLine = System.lineSeparator();
        FavoriteProgrammingLanguage.main(args);
        String[] lines = systemOutRule.getLog().split(newLine);

        String failMessage =  "Expected to have multiple input prompts and 1 output line";
        assertEquals(failMessage, lines.length, 13);

        failMessage = "Expected output to contain \"" + expected + "\"";
        assertEquals(failMessage, expected, lines[lines.length - 1]);

    }
}
