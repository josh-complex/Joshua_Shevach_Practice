## Katas for Lesson 5

## Introduction

In this lesson, you learned about data structures, such as arrays, and why they are useful.

In these katas, you will combine rour prior knowledge of methods and with yiur new knowledge of arrays.

Read the requirements and follow the instructions to successfully complete these katas.

## Requirements for all Katas

- Add the code necessary to the `App` class found in the project `U1M1L5Katas`.

- All methods should be public and static.

- The requirements for each method are listed in the relevant section.

- Tests can be found in `AppTest`. Once you have written each method, uncomment the associated test and run `AppTest`.

- All code and calculations should be done manually. Do NOT use helper libraries to find these answers.

## Kata 1: Total

### Instructions

In this kata, you will enter a range of numbers and return the sum of those numbers.

1. Locate `total` in the starter code.

2. Create a method called `total()` that returns the sum of all the values in an array of ints. Your code should work for an array of any size.

   - Example input:[0, 1, 2, 3, 4, 5, 6, 7] 
   
   - Example output: 28

## Kata 2: Total Odd

### Instructions

In this kata, you will enter an array of numbers and return the sum of the odd numbers.

1. Locate `totalOdd` in the starter code.

2. Create a method called `totalOdd()` that returns the sum of only the values at the odd-numbered indexes in an array of ints. Your code should work for an array of any size.

   - Example input: [0, 2, 1, 8, 3, 9, 1, 4]

   - Example output: 23

## Kata 3: Total Even

### Instructions

In this kata, you will enter an array of numbers and return the sum of the even numbers.

1. Locate `totalEven` in the starter code.

2. Create a method called `totalEven()` that returns the sum of only the values at the even-numbered indexes in an array of ints. Your code should work for an array of any size.

   - Example input: [6, 11, 7, 2, 4, 10, 1, 5]

   - Example output: 18

## Kata 4: Second Largest Number

### Instructions

In this kata, you will enter an array of numbers and return the second largest number of the group.

1. Locate `secondLargestNumber` in the starter code.

2. Create a method called `secondLargestNumber()` that returns the second largest number in an array of ints. Your code should work for an array of any size. Assume that your input array will always have a length of at least two ints.

   - Example input: [0, 1, 2, 3, 4, 5, 6, 7]

   - Example output: 6

## Kata 5: Swap First and Last

### Instructions

In this kata, you will enter an array of letters and swap the first and last of the group.

1. Locate `swapFirstAndLast` in the starter code.

2. Create a method called `swapFirstAndLast()` that takes in an array of strings, swaps the first and last elements, and returns the array. Your code should work for an array of any size.

   - Example input: `["foo", "bar", "baz", "qux"]`

   - Example output: `["qux", "bar", "baz", "foo"]`

## Kata 6: Reverse

### Instructions

In this kata, you will enter an array of numbers and return a new array that is the reverse of the first.

1. Locate `reverse` in the starter code.

2. Create a method called `reverse()` that takes in an array of ints and returns a new array with the array reversed. Your code should work for an array of any size.

   - Example input: `[0, 1, 2, 3, 4, 5, 6, 7]`

   - Example output: `[7, 6, 5, 4, 3, 2, 1, 0]`

## Kata 7: Concatenate String

### Instructions

In this kata, you will enter an array of letters and return a new array that is a condensed version of the original.

1. Locate `concatenateString` in the starter code.

2. Create a method called `concatenateString()` that takes in an array of strings and returns a string that consists of the concatenation of all elements in the array. Your code should work for an array of any size.

   - Example input: `["foo", "bar", "baz"]`

   - Example output: `"foobarbaz"`

## Kata 8: Every Third

### Instructions

In this kata, you will enter an array of numbers and return a new array that lists every third number. If this does not exist within your original array, return `null`.

1. Locate `everyThird` in the starter code.

2. Create a method called `everyThird()` that takes in an array of ints and returns an array that consists of every third element. Your code should work for an array of any size. If there are fewer than three elements in the array, return `null`.

   - Example input: `[0, 1, 2, 3, 4, 5, 6, 7]`

   - Example output: `[2, 5]`

## Kata 9: Less Than Five

### Instructions

In this kata, you will enter an array of numbers and find the ones less than five. Your new array will return those numbers. If this does not exist within your original array, return `null`.

1. Locate `lessThanFive` in the starter code.

2. Create a method called `lessThanFive()` that takes in an array of ints, finds the elements that are less than five, and returns an array containing those elements. Your code should work for an array of any size. If there are no elements less than five, return `null`.

3. Determine how many values are less than five.

4. Create a new array to hold the values under five.

5. Copy all the values under five from the original array to the new array.

   - Example input: `[0, 1, 2, 3, 4, 5, 6, 7]`

   - Example output: `[0, 1, 2, 3, 4]`

## Challenge Kata: 1

### Instructions

In this kata, you will enter an array of numbers. You will then return two new arrays (one that has the numbers from the original array that are less than five and one that has the numbers from the original array greater than or equal to five).

1. Locate `splitAtFive` in the starter code.

2. Create a method called `splitAtFive()` that takes in an array of ints. This method should split the array into two new arrays, one holding the values less than five and the other holding the values equal to or greater than five.

3. Return a two-dimensional array, with the small-value array first. Your code should work for an array of any size.

## Challenge Kata: 2

### Instructions

In this kata, you will enter an array of numbers. You will then return two new arrays (one that has the even numbers from the original array and one that has the odd numbers from the original array).

1. Locate `evensAndOdds` in the starter code.

2. Create a method called `evensAndOdds()` that takes in an array of strings. Create two new arrays, one holding the values at the even indices and one holding the values at the odd indices.

3. Return a two-dimensional array, with the even-indices array first. Your code should work for an array of any size.


---

© 2021 Trilogy Education Services, a 2U, Inc. brand. All Rights Reserved.
