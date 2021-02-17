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

import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class RectPavingCompanyTest {

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
    public void shouldOutputLengthWidthAreaPerimeterAndCostWhenGivenTwoInputs() throws Exception {
        String[][] results = {
                {"1400", "150", "17500.00", "1237.50"},
                {"100", "50", "1250.00", "412.50"},
                {"300", "80", "3750.00", "660.00"}
        };
        String[] args = null;

        int iteration = 0;

        while (iteration < 3) {
            setIn("MultipleInputs", iteration);
            RectPavingCompany.main(args);
            String output = systemOutRule.getLog();
            for(String result : results[iteration]) {
                String message = "\nExpected output to contain " + result + "\n";
                assertTrue(message, output.contains(result));
            }

            systemOutRule.clearLog();
            iteration++;
        }

    }


}
