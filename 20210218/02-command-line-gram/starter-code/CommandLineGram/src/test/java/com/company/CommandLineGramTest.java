package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class CommandLineGramTest {

    private InputStream original;

    private static void setIn(String dir, int iteration) throws FileNotFoundException {
        final FileInputStream fips = new FileInputStream(new File("testfiles/" + dir + "/input" + iteration + ".txt"));
        System.setIn(fips);
    }

    private static String[] readFile(String dir, int iteration) throws Exception {
        final FileInputStream fips = new FileInputStream(new File("testfiles/" + dir + "/input" + iteration + ".txt"));
        Scanner scan = new Scanner(fips);

        String[] arr = new String[13];

        int i = 0;
        while(scan.hasNext()) {
            arr[i] = scan.nextLine();
            i++;
        }

        return arr;
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
    public void shouldPrintAllInputsWhenLastInputIsGiven() throws Exception {

        String[] args = null;

        int iteration = 0;

        while (iteration < 3) {
            setIn("MultipleInputs", iteration);
            String[]  results = readFile("MultipleInputs", iteration);
            CommandLineGram.main(args);
            String output = systemOutRule.getLog();
            for(String result : results) {
                String message = "\nExpected output to contain " + result + "\n";
                assertTrue(message, output.contains(result));
            }

            systemOutRule.clearLog();
            iteration++;
        }

    }


}
