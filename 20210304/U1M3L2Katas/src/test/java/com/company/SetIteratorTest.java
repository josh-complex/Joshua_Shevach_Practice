// package com.company;

// import org.junit.Before;
// import org.junit.Rule;
// import org.junit.Test;
// import org.junit.contrib.java.lang.system.SystemOutRule;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;

// import static org.junit.Assert.assertEquals;

// public class SetIteratorTest {

//     private static String readFile(String fileName) throws IOException {
//         BufferedReader br = new BufferedReader(new FileReader(fileName));
//         try {
//             StringBuilder sb = new StringBuilder();

//             String line = br.readLine();
//             while (line != null) {
//                 sb.append(line);
//                 sb.append(System.lineSeparator());
//                 line = br.readLine();
//             }
//             return sb.toString();
//         } finally {
//             br.close();
//         }
//     }

//     @Rule
//     public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

//     @Test
//     public void testPrintSet() throws IOException {
//         SetIterator tester = new SetIterator();
//         String failMessage = "Expect output to match.";

//         String expected = readFile("testfiles/output1.txt");
//         tester.printSet(1,2,3,4,5);
//         assertEquals(failMessage, expected, systemOutRule.getLog());
//         systemOutRule.clearLog();

//         expected = readFile("testfiles/output2.txt");
//         tester.printSet(3,1,3,4,1);
//         assertEquals(failMessage, expected, systemOutRule.getLog());
//         systemOutRule.clearLog();

//         expected = readFile("testfiles/output3.txt");
//         tester.printSet(8,8,9,2,1);
//         assertEquals(failMessage, expected, systemOutRule.getLog());
//         systemOutRule.clearLog();
//     }
// }
