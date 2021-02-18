package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class CountByThirteenTest {

    private InputStream original;

    private static void setIn(String dir, int iteration) throws FileNotFoundException  {
        final FileInputStream fips = new FileInputStream(new File("testfiles/" + dir + "/input" + iteration + ".txt"));
        System.setIn(fips);
    }

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
}
