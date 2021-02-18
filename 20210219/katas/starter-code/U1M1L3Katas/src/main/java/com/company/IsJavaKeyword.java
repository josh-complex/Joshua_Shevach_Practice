package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IsJavaKeyword {

    public static void main(String[] args) {

        List<String> keywords = Arrays.asList("abstract", "assert", "boolean", "break", "byte", "case", "catch",
                "char", "class", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally",
                "float", "for", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new",
                "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super",
                "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while",
                "const", "goto");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string to check");
        String stringToCheck = scanner.nextLine();

        if (keywords.contains(stringToCheck.toLowerCase())) {
            System.out.println("This is a Java keyword");
        } else {
            System.out.println("This is not a Java keyword");
        }

    }
}
