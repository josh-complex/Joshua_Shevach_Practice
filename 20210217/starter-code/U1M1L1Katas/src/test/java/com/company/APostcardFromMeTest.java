package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class APostcardFromMeTest {

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
    public void shouldOutputAsExpectedForAPostcardFromMe() throws Exception {
        String[] args = null;

        String output = readFile("testfiles/APostcardFromMe/output.txt");
        APostcardFromMe.main(args);
        String actual = systemOutRule.getLog();
        String message = "\nExpected :\n" + output + "\nActual:\n" + actual;
        assertEquals(message, output, actual);
    }
}
