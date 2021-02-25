# 1.2.5 Activity: UserIO

## Activity: Implementation UserIO Interface

## Introduction

In this activity, you will be provided with starter code that contains a `UserIO` interface and an `Application()` method. You’ll be asked to create a new class called `Input` that implements the provided `UserIO` interface.

## Requirements

- All code must be contained inside your `Input` class.

- You should not modify the `UserIO` interface or the `Application` class.

## Instructions

1. Create a new class called `Input` that implements the provided `UserIO` interface. This interface defines the following methods:

    - `readInt`, which displays a prompt and then reads an `int` from the user.

    - `readLong`, which displays a prompt reading a `long` from the user.

    - `readDouble`, which displays a prompt reading a `double` from the user.

    - `readFloat`, which displays a prompt reading a `float` from the user.

    - `readString`, which displays a prompt reading a `string` from the user.

    The methods in your `Input` class must contain the logic to read the value in, convert it to the appropriate type, and return it to the user. A `main()` method that uses this class in the `Application` class has been provided.

2. Once you have implemented this class, run `Application` to test your code.

3. All code must be contained inside your `Input` class. You should not modify the `UserIO` interface or the `Application` class.

**Hint**: You'll need to use `Scanner` in your `Input` class.

## Challenge Activity: BetterInput

If a user is asked to enter an integer value and instead inputs a string, Java will throw an `Exception`&mdash;meaning that something went wrong and that Java generated some kind of error because of it.

1. Create a new class called `BetterInput` that implements the `UserIO` interface but also handles exceptions thrown because of bad user input. When bad input is received, your methods should continue to prompt users for input until valid input is received. You will need to use a `try...catch` block as well as `recursion` to most effectively accomplish this.

2. Once you've completed that, change `userInput` to instantiate `BetterInput` instead of `Input`, and run `Application` to test your code.

For more information, see the [W3Schools page on try...catch blocks](https://www.w3schools.com/java/java_try_catch.asp) and this [tutorial on recursion in Java](https://www.java-samples.com/showtutorial.php?tutorialid=151).

**Hint:** If an error occurs, the method should call itself again with the same prompt as before.

---

© 2021 Trilogy Education Services, a 2U, Inc. brand. All Rights Reserved.
