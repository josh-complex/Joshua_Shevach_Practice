## Prime Finder

## Introduction

In this activity, you will write a program that takes a user-inputted number and prints all the prime numbers from 1 to the user-inputted number. This activity will test your knowledge of `for` loops. Read the requirements and follow the instructions to successfully complete this activity.

## Requirements

- Complete the following requirements in the `PrimeFinder` project found in the `starter-code` directory.

- All code must be contained within the `main()` method of the `PrimeFinder` class.

- The requirements must be implemented in the order provided.

- Once your code has been written, it can be tested using `PrimeFinderTest`.

## Instructions

1. Write a program that takes a user-inputted number and prints all the prime numbers from 1 to the user input (inclusive).

2. See the hints provided for help with this task.

### Hints

- A **prime number** is a number that has no factors except for 1 and the number itself.

- Even numbers, by definition, cannot be prime.

- You can use the modulus operator (`%`) to check whether one number divides evenly into another number. The modulus operator calculates the remainder after dividing one number into another. If the remainder is 0, the second number divides evenly into the first, meaning that it is a factor of that number.

  - Examples:

    - `6%2 = 0`: 2 goes into 6 three times with no remainder.

    - `5%3 = 2`: 3 goes into 5 one time with a remainder of 2.

- One way to check whether a number is prime is to check whether any of the numbers less than that number (excluding 1) divide evenly into it. A `for` loop can be used for this.

  - For example, if we want to see if 5 is prime, we would check the following:

    - `5%2 == 0 // false`: 2 goes into 5 twice with a remainder of 1.

    - `5%3 == 0 // false`: 3 goes into 5 once with a remainder of 2.

    - `5%4 == 0 // false`: 4 goes into 5 once with a remainder of 1.

    - None of these numbers (2, 3, 4) divide evenly into 5, so 5 is a prime number.

  - For example, if we want to see if 4 is prime, we would check to see if:

    - `4%2 == 0 // true`: 2 goes into 4 twice with a remainder of 0.

    - `4%3 == 0 // false`: 3 goes into 4 once with a remainder of 1.

    - 2 goes into 4 evenly, so 4 is not prime.

- Start by writing a program that just tells you whether one given number is prime. Then modify your program to meet the requirement to print all the prime numbers from 1 to the user input.

---

© 2021 Trilogy Education Services, a 2U, Inc. brand. All Rights Reserved.
