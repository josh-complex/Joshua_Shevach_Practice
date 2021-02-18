package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;


public class LoanCalculatorTest {

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
    public void shouldOutputMonthlyPaymentWhenGivenPrincipalTermAndInterestRate() throws Exception {
        String[] args = null;
        String[] results = {"988.14", "669.08", "572.03"};

        int iteration = 0;

        while( iteration < 3 ) {
            setIn("inputs", iteration);
            LoanCalculator.main(args);

            String failMessage = "Calculated loan total should be displayed. Expected: " + results[iteration];
            assertTrue(failMessage, systemOutRule.getLog().contains(results[iteration]));
            systemOutRule.clearLog();
            iteration++;
        }
    }
}
