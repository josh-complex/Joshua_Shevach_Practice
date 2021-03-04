package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    public static int total(List<Integer> numbers) {

        int sum = 0;
        for (int num : numbers)
            sum += num;

        return sum;
    }

    public static int totalEven(List<Integer> numbers) {

        int sum = 0;
        for (int i = 0; i < numbers.size(); i += 2)
            sum += numbers.get(i);

        return sum;
    }

    public static List<String> swapFirstAndLast(List<String> strings) {

        String temp = strings.get(0);
        strings.set(0, strings.get(strings.size() - 1));
        strings.set(strings.size() - 1, temp);

        return strings;
    }

    public static List<Integer> reverse(List<Integer> numbers) {

        Collections.reverse(numbers);
        return numbers;
    }

    public static List<Integer> lessThanFive(List<Integer> numbers) {

        List<Integer> lessThan = numbers.stream()
                .filter(x -> x < 5)
                .collect(Collectors.toList());
        return lessThan.size() > 0 ? lessThan : null;
    }

    //challenge
    public static List<List<Integer>> splitAtFive(List<Integer> numbers) {

        List<Integer> less = numbers.stream()
                .filter(x -> x < 5)
                .collect(Collectors.toList());
        List<Integer> more = numbers.stream()
                .filter(x -> x >= 5)
                .collect(Collectors.toList());

        return new ArrayList<List<Integer>>(){{
            add(less);
            add(more);
        }};
    }

    public static List<List<String>> evensAndOdds(List<String> strings) {

        List<String> evens = IntStream.range(0, strings.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(strings::get)
                .collect(Collectors.toList());
        List<String> odds = IntStream.range(0, strings.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(strings::get)
                .collect(Collectors.toList());

        return new ArrayList<List<String>>() {{
            add(evens);
            add(odds);
        }};
    }
}
