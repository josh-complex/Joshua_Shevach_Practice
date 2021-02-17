package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class LuxuryTaxCalculatorTest {

    private InputStream original;

    private static void setIn(String dir, int iteration) throws FileNotFoundException {
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
    public void shouldCalculateTaxesWhenTotalSalaryIsGreaterThanFortyMillion() throws Exception {
        String[] totals = {"60000000", "30000000", "20000000"};
        String[] taxes = {"3600000", "-1800000", "-3600000"};
        String[] args = null;

        int iteration = 0;

        while (iteration < 3) {
            setIn("MultipleInputs", iteration);
            LuxuryTaxCalculator.main(args);
            String output = systemOutRule.getLog();

            String curr = totals[iteration];

            String message = "\nExpected output to contain " + curr + "\n";
            assertTrue(message, output.contains(curr));

            if (Integer.parseInt(curr) > 40000000) {
                message = "\nExpected output to contain " + taxes[iteration] + "\n";
                assertTrue(message, output.contains(taxes[iteration]));
            } else {
                message = "\nExpected output to not contain " + taxes[iteration] + "\n";
                assertFalse(message, output.contains(taxes[iteration]));
            }

            systemOutRule.clearLog();
            iteration++;
        }

    }


}
