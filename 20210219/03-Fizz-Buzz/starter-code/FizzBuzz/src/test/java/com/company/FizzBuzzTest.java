package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {

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
    public void shouldPrintFizzOrBuzzOrBothWhenNumberIsDivisibleByThreeOrFiveOrBoth() throws Exception {
        String[] args = null;
        FizzBuzz.main(args);

        String expected = readFile("testfiles/output/output.txt");
        String failMessage = "Expect output to match.";
        assertEquals(failMessage, expected, systemOutRule.getLog());
    }
}
