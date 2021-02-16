package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FormattedOutputTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void shouldOutputAsExpectedForFormattedOutput() throws Exception {
        String[] args = null;

        FormattedOutput.main(args);

        String output = systemOutRule.getLog();

        assertTrue("Expected output to contain \"My name is\"", output.contains("My name is"));
        assertTrue("Expected output to contain \"and I am\"",output.contains("and I am"));
        assertTrue("Expected output to contain \"years old\"",output.contains("years old"));
    }
}
