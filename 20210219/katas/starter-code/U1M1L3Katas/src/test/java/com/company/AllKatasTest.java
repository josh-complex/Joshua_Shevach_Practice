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

public class AllKatasTest {

    private InputStream original;

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
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

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

    @Test
    public void shouldCountByThirteenUpToGivenNumberWhenGivenNumber() throws Exception {
        String[] args = null;

        int iteration = 0;

        while( iteration < 3 ) {
            setIn("CountByThirteen", iteration);
            CountByThirteen.main(args);

            String expected = readFile("testfiles/CountByThirteen/output" + iteration + ".txt");

            // Remove input prompt from the console output
            String[] split = systemOutRule.getLog().split(System.lineSeparator());
            String actual = "";
            for(int i = 1; i < split.length; i++) {
                actual += split[i] + System.lineSeparator();
            }

            assertEquals(expected, actual);
            systemOutRule.clearLog();
            iteration++;
        }


    }

    @Test
    public void shouldCountByTwoUpToGivenNumberWhenGivenNumber() throws Exception {
        String[] args = null;

        int iteration = 0;

        while( iteration < 3 ) {
            setIn("CountByTwo", iteration);
            CountByTwo.main(args);

            String expected = readFile("testfiles/CountByTwo/output" + iteration + ".txt");

            // Remove input prompt from the console output
            String[] split = systemOutRule.getLog().split(System.lineSeparator());
            String actual = "";
            for(int i = 1; i < split.length; i++) {
                actual += split[i] + System.lineSeparator();
            }

            assertEquals(expected, actual);
            systemOutRule.clearLog();
            iteration++;
        }
    }

    @Test
    public void shouldCountUpToGivenNumberWhenGivenNumber() throws Exception {
        String[] args = null;

        int iteration = 0;

        while( iteration < 3 ) {
            setIn("CountTo", iteration);
            CountTo.main(args);

            String expected = readFile("testfiles/CountTo/output" + iteration + ".txt");

            // Remove input prompt from the console output
            String[] split = systemOutRule.getLog().split(System.lineSeparator());
            String actual = "";
            for(int i = 1; i < split.length; i++) {
                actual += split[i] + System.lineSeparator();
            }

            assertEquals(expected, actual);
            systemOutRule.clearLog();
            iteration++;
        }
    }

    @Test
    public void shouldPrintEvenOrOddWhenInputIsEvenOrOdd() throws Exception {
        String[] args = null;

        String[] results = {"even", "odd", "odd"};

        int iteration = 0;

        while (iteration < results.length) {
            // set input to file stream
            setIn("EvenOrOdd", iteration);

            // run main method and capture output
            EvenOrOdd.main(args);
            String output = systemOutRule.getLog().toLowerCase();

            // check that the current iteration output contains intended result
            String message = "\nExpected output to contain " + results[iteration] + "\n";
            assertTrue(message, output.contains(results[iteration]));

            // clear log and increment iteration
            systemOutRule.clearLog();
            iteration++;
        }

    }

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

    @Test
    public void shouldAskForNumberBetweenFifteenAndThirtyTwoUntilCorrectAnswerIsGivenWhenInputIsGiven() throws Exception {
        String[] args = null;
        String expected = "30";

        setIn("RangeChecker", 0);
        String newLine = System.lineSeparator();
        RangeChecker.main(args);
        String[] lines = systemOutRule.getLog().split(newLine);

        String failMessage =  "Expected to have multiple input prompts and 1 output line";
        assertEquals(failMessage, lines.length, 15);

        failMessage = "Expected output to contain the number which passed the range check";
        assertTrue(failMessage, lines[lines.length - 1].contains(expected));
    }

    @Test
    public void shouldContinueToAskForInputUntilFortyTwoIsGivenWhenInputIsGiven() throws Exception {
        String[] args = null;
        String expected = "That's the number I was looking for! 42 is definitely the answer!";

        setIn("UltimateQuestion", 0);
        String newLine = System.lineSeparator();
        UltimateQuestion.main(args);
        String[] lines = systemOutRule.getLog().split(newLine);

        String failMessage =  "Expected to have multiple input prompts and 1 output line";
        assertEquals(failMessage, lines.length, 43);

        failMessage = "Expected output to contain \"" + expected + "\"";
        assertEquals(failMessage, expected, lines[lines.length - 1]);
    }

    @Test
    public void shouldPrintExpectedActionWhenAgeIsGiven() throws Exception {
        String[] args = null;
        String[] results = {"vote", "alcohol", "president", "aarp", "retire", "octogenarian", "century"};

        String newLine = System.lineSeparator();

        int iterations = 0;

        setIn("YourAgeCan", iterations);
        YourAgeCan.main(args);
        String[] lines = systemOutRule.getLog().split(newLine);
        String failMessage = "Expected no output if age is less than 18";
        assertEquals(lines.length, 1);
        iterations++;
        systemOutRule.clearLog();

        while (iterations < 8) {
            setIn("YourAgeCan", iterations);
            YourAgeCan.main(args);
            String actual = systemOutRule.getLog().toLowerCase();
            failMessage = "Expected all outputs that the age qualified for.";
            if (iterations < 6) {
                for(int i = 0; i < iterations; i++) {
                    assertTrue(failMessage, actual.contains(results[i]));
                }
            } else {
                for(int i = 0; i < 5; i++) {
                    assertTrue(failMessage, actual.contains(results[i]));
                }

                if (iterations == 7) {
                    assertTrue(failMessage, actual.contains(results[6]));
                }
            }

            systemOutRule.clearLog();
            iterations++;
        }

    }

}
