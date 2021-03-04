package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinkedListApp {

    public static int total(LinkedList<Integer> numbers) {

        int sum = 0;
        for (int num : numbers)
            sum += num;

        return sum;
    }

    public static int totalEven(LinkedList<Integer> numbers) {

        int sum = 0;
        for (int i = 0; i < numbers.size(); i += 2)
            sum += numbers.get(i);

        return sum;
    }

    public static LinkedList<String> swapFirstAndLast(LinkedList<String> strings) {

        String temp = strings.get(0);
        strings.set(0, strings.get(strings.size() - 1));
        strings.set(strings.size() - 1, temp);

        return strings;
    }

    public static LinkedList<Integer> reverse(LinkedList<Integer> numbers) {

        Collections.reverse(numbers);
        return numbers;
    }

    public static LinkedList<Integer> lessThanFive(LinkedList<Integer> numbers) {

        LinkedList<Integer> lessThan = numbers.stream()
                .filter(x -> x < 5)
                .collect(Collectors.toCollection(LinkedList::new));
        return lessThan.size() > 0 ? lessThan : null;
    }

    //challenge
    public static LinkedList<LinkedList<Integer>> splitAtFive(LinkedList<Integer> numbers) {

        LinkedList<Integer> less = numbers.stream()
                .filter(x -> x < 5)
                .collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Integer> more = numbers.stream()
                .filter(x -> x >= 5)
                .collect(Collectors.toCollection(LinkedList::new));

        return new LinkedList<LinkedList<Integer>>(){{
            add(less);
            add(more);
        }};
    }

    public static LinkedList<LinkedList<String>> evensAndOdds(LinkedList<String> strings) {

        LinkedList<String> evens = IntStream.range(0, strings.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(strings::get)
                .collect(Collectors.toCollection(LinkedList::new));
        LinkedList<String> odds = IntStream.range(0, strings.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(strings::get)
                .collect(Collectors.toCollection(LinkedList::new));

        return new LinkedList<LinkedList<String>>() {{
            add(evens);
            add(odds);
        }};
    }
}