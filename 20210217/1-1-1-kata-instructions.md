## Introduction

**Katas** are short exercises designed to help programmers hone their skills through practice. The term comes from Japanese martial arts, where it means “form” and refers to a specific exercise designed to be practiced alone and then combined to form more complex procedures.

In these katas, you will practice the skills learned in class as you add code to particular Java classes to generate specific outcomes.

## Requirements for All Katas

- All solutions must be contained within the `U1M1L1Katas` project found in the `starter-code` directory.

- Once all solutions have been written, they can be tested at the same time using `AllKatasTest`.

## Kata 1: A Chatty Program

### Instructions

In `AChattyProgram`, complete the following steps.

1. Add code to your `main()` method that produces the following output:

    ```
    Hi!
    How are you?
    I'm fine!
    Doing really well in fact...
    Isn't it a beautiful day?
    Smiling is my favorite!
    Good bye!!
    ```

2. Run `AChattyProgramTest` to confirm that your output is correct.

## Kata 2: A Postcard from Me

### Instructions

In `APostcardFromMe`, complete the following steps.

1. Add code to your `main()` method that produces output identical to the following:

    ```java
    *------------------------------------------------------------------*
    | John Smith                                                       |
    | 123 Main Street                                                  |
    | Hometown, MN 55555                                               |
    |                                                                  |
    |                        Jane Doe                                  |
    |                        345 Mockingbird Ln.                       |
    |                        Smalltown, OR 99999                       |
    |                                                                  |
    |                                                                  |
    *------------------------------------------------------------------*
    ```

2. Run `APostcardFromMeTest` to confirm that your output is correct.

## Kata 3: Marvel Table

### Instructions

In the class `MarvelTable` complete the following steps.

1. Add code to your `main()` method that produces output identical to the following:

    ```java
    -------------------------------------------------------------
    |   Hero              |   Affiliation     |   Power         |
    -------------------------------------------------------------
    |   Captain America   |   The Avengers    |   Peak Human    |
    -------------------------------------------------------------
    |   Wolverine         |   The X-Men       |   Healing/Claws |
    -------------------------------------------------------------
    |   Cyclops           |   The X-Men       |   Eye Beam      |
    -------------------------------------------------------------
    |   Domino            |   X-Force         |   Luck          |
    -------------------------------------------------------------
    ```

2. Run `MarvelTableTest` to confirm that your output is correct.

## Challenge

If you've completed the previous katas, complete the following one for an extra challenge.

## Kata: Formatted Output

### Introduction

So far, we've used `println()` to print text to the standard output (`System.out`). Java also provides a method called `format()`, which we can use to convert numbers to text for easier outputting. We'll delve into some of these concepts further in the next lesson, but for now, review the basics here.

See the following code for an example:

```java
System.out.format("The value of the float is %f, while the value of the integer is %d, and the string is %s", 4.23, 5, "cat"); 
```

The preceding code will have the following output:

```java
The value of the float is 4.230000, while the value of the integer is 5, and the string is cat
```

Take a second to read through the code and try to understand how each piece of the puzzle comes together to form that output.

Important features to note:

- `%f` specifies that the value is a `float`, or a number that includes values after the decimal point.

- `%d` specifies that the value is an `integer`, or a whole number.

- `%s` specifies that the value is a `string`, or simply text, denoted by the double-quotes surrounding it.

Again, we'll cover all of this in more detail during the next lesson, but a core aspect of being a developer is being able to extrapolate from a given pattern.

It may seem like a little bit of a challenge, but you've got this!

### Instructions

In the class `FormattedOutput`, complete the following steps.

1. Add code to your `main()` method that produces output similar to the following, using `System.out.format()`, the appropriate format specifier, and the value for both your name and age:

    ```java
    My name is <YOUR NAME> and I am <YOUR AGE> years old.
    ```

2. Run `FormattedOutputTest` to confirm that your output is correct.