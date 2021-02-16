package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AllKatasTest {

    private static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void shouldOutputAsExpectedForAChattyProgram() throws Exception {
        String[] args = null;

        String output = readFile("testfiles/AChattyProgram/output.txt");
        AChattyProgram.main(args);
        String actual = systemOutRule.getLog();
        String message = "\nExpected :\n" + output + "\nActual:\n" + actual;
        assertEquals(message, output, actual);
    }

    @Test
    public void shouldOutputAsExpectedForAPostcardFromMe() throws Exception {
        String[] args = null;

        String output = readFile("testfiles/APostcardFromMe/output.txt");
        APostcardFromMe.main(args);
        String actual = systemOutRule.getLog();
        String message = "\nExpected :\n" + output + "\nActual:\n" + actual;
        assertEquals(message, output, actual);
    }

    @Test
    public void shouldOutputAsExpectedForFormattedOutput() throws Exception {
        String[] args = null;

        FormattedOutput.main(args);

        String output = systemOutRule.getLog();

        assertTrue("Expected output to contain \"My name is\"", output.contains("My name is"));
        assertTrue("Expected output to contain \"and I am\"",output.contains("and I am"));
        assertTrue("Expected output to contain \"years old\"",output.contains("years old"));
    }

    @Test
    public void shouldOutputAsExpectedForMarvelTable() throws Exception {
        String[] args = null;

        String output = readFile("testfiles/MarvelTable/output.txt");
        MarvelTable.main(args);
        String actual = systemOutRule.getLog();
        String message = "\nExpected :\n" + output + "\nActual:\n" + actual;
        assertEquals(message, output, actual);
    }
}