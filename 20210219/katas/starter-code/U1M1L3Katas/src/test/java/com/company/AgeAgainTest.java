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

public class AgeAgainTest {

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
    public void shouldAskExpectedQuestionsWhenCertainAgeIsGiven() throws Exception {

        String[] args = null;
        String[][] tests = {
                {"10", "5th"},
                {"9", "4th"},
                {"13", "8th"},
                {"15", "yes", "Rutgers"},
                {"14", "no", "programmer"},
                {"18", "yes", "Columbia"},
                {"19", "astronaut"},
                {"20", "congressman"},
                {"60", "veterinarian"}
        };

        for(int i = 0; i < tests.length; i++) {
            setIn("AgeAgain", i);
            AgeAgain.main(args);
            String output = systemOutRule.getLog();

            int age = Integer.parseInt(tests[i][0]);
            if( age < 14) {
                String expected = "Wow! " + tests[i][1] + " grade - that sounds exciting!";
                String failMessage = "\nExpected output to contain \"Wow! <input> grade - that sounds exciting!\"\n";
                assertTrue(failMessage, output.contains(expected));
            } else if (age >= 14 && age <= 18) {
                if (tests[i][1] == "yes") {
                    String expected = tests[i][2] + " is a great school!";
                    String failMessage = "\nExpected output to contain \"<input> is a great school!\"\n";
                    assertTrue(failMessage, output.contains(expected));
                } else {
                    String expected = "Wow, " + tests[i][2] + " sounds like a plan!";
                    String failMessage = "\nExpected output to contain \"Wow, <input> sounds like a plan!\"\n";
                    assertTrue(failMessage, output.contains(expected));
                }
            } else {
                String expected = tests[i][1] + " sounds like a great job!";
                String failMessage = "\nExpected output to contain \"<input> sounds like a great job!\"\n";
                assertTrue(failMessage, output.contains(expected));
            }

            systemOutRule.clearLog();
        }

    }
}



