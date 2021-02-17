## Luxury Tax Calculator

## Requirements

- Complete the following requirements in the `LuxuryTaxCalculator` project found in the `starter-code `directory.

- All code must be contained within the `main()` method of the `LuxuryTaxCalculator` class.

- The requirements must be implemented in the order provided.

- Once your code has been written, it can be tested using `LuxuryTaxCalculatorTest`.

## Relevant Reading

Sometimes we only want to execute certain portions of code if a certain condition is true. To do so, we use an `if` statement, as follows:

```java
if (/* some condition */) {
  // If this condition is true, run some code
}
```

Let's parse through this code. We use the keyword `if`, followed by parentheses that contain the conditional statement we are testing. If this is a true statement, then we run the code that's located inside the braces (`{}`). If the statement is false, then the code block is essentially ignored and never executed.

For a more complete example, look at the following code snippet:

```java
int x = 5;
int y = 3;

if (x < y) {
  System.out.println("This code will never run");
}

if (x > y) {
  System.out.println("Of course 5 is bigger than 3! This code runs!");
}
```

Notice that we're using greater than (`>`) and less than (`<`) operators. These work exactly as you might remember from math class.

In the first `if` statement, if `x` is less than `y`, then the program will execute the `println()` command inside that code block. Of course, this never happens, because `x` is not less than `y`.

In the second `if` statement, the condition (`x` is greater than `y`) is true. Thus, the program will output `Of course 5 is bigger than 3! This code runs!`

There are several comparison operators, but let's review the following three for now:

- **Greater than**, or `>`, checks whether the value on the left is larger than the one on the right.

- **Less than**, or `<`, checks whether the value on the left is smaller than the one of the right (the inverse of greater than).

- **Equal to**, or `==`, checks whether the two values are equal to each other. Since a single equals sign (`=`) is used to assign values to a variable, we use the double equals sign to make a comparison.

The `if` statement is one of the core building blocks of all coding languages. Take a few minutes to ensure that you understand the basics, then tackle the assignment.

## Instructions

The purpose of this program is to calculate how much luxury tax a hypothetical sports team will pay to sign three players. Here are the requirements:

- Prompt the user to enter the salary for Player 1 (without the dollar sign or commas).

- Prompt the user to enter the salary for Player 2 (without the dollar sign or commas).

- Prompt the user to enter the salary for Player 3 (without the dollar sign or commas).

- Calculate and display the total salary for all three players.

- If the team exceeded their spending limit, calculate and display the cost of the luxury tax.

  - The team's spending limit is $40,000,000.

  - The tax rate is 18%. Note that the 18% luxury tax is only paid on the the amount of salary OVER the team's spending limit. For example, if a team's payroll is $55,000,000, they only pay luxury tax on $15,000,000.


  ---

© 2021 Trilogy Education Services, a 2U, Inc. brand. All Rights Reserved.
