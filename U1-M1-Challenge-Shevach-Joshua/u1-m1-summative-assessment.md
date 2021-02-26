# Challenge: Java Tools and Language

## Concepts

This assessment contains four coding exercises that will evaluate your understanding of Java tools and language concepts learned throughout this module.

## Challenge Set Up

1. **Pull instructions:** In your class Git repository, execute `git pull origin master` to pull down the latest instructions for the Challenge Assessment.

2. **Create a new folder:** Inside the folder called "Working Folder Java" create a folder called `U1-M1-Challenge-Lastname-Firstname`.

3. **Copy instructions:** Copy the challenge instructions from your class Git repository folder to the `U1-M1-Challenge-Lastname-Firstname` folder you just created.

4. **Complete your challenge:** Work on and complete your Challenge in that folder. The folder may contain multiple IntelliJ projects.If so, open each project folder separately in IntelliJ. Remember that you can identify an IntelliJ project folder because it contains a `pom.xml` file. Find the `pom.xml` file and open the parent folder of the pom file in order to properly open the project. 

## Exercise 1: Month Converter

### Requirements

The first exercise is called Month Converter and focuses on your understanding of conditionals. This program converts a number entered by the user into a month. You must provide one version that uses an if/else/else-if structure and one version that uses a switch statement. Your solution must consist of the following two files:

- `MonthConverterIf`
- `MonthConverterSwitch`

### Instructions

Follow the steps below to complete the Month Converter Exercise:

1. Prompt the user to enter a number between 1 and 12.

2. Print out the name of the month corresponding to its number.

3. Print out the following error message if the user enters a number less than 1 or greater than 12: "You have entered an invalid number. You must enter a number between 1 and 12. Goodbye."

## Exercise 2: Valid Number

### Requirements

The second exercise is called Valid Number and focuses on your understanding of loops. This program prompts the user for a number between 1 and 10 and keeps asking until the entered number is in range. Your solution must be in a Java file called `ValidNumber`.

### Instructions

Follow the steps below to complete the Valid Number Exercise. The program must do the following:

1. Prompt the user to enter a number between 1 and 10.

2. If the user enters a number less than 1 or greater than 10, display the following error message and prompt the user for another entry: "You must enter a number between 1 and 10. Please try again."

3. When the user enters a valid number, print the number to the screen and exit.

## Exercise 3: Some Math

### Requirements

The third exercise is called Some Math and focuses on your understanding of methods. In this program, you will create three methods and call them from main with the specified parameters. Your solution must be in a Java file called `SomeMath`.

### Instructions

Follow the steps below to complete the Some Math Exercise. The program must do the following:

1. Create a method called `total5`. This method must take the 5 integer parameters 1, 2, 3, 4, 5 and return the sum total. You must call the method and print the returned total to the screen.

2. Create a method called `average5`. This method must take 5 integer parameters 1, 3, 5, 7, 9 and return the average of the numbers as a double. You must call the method and print the returned average to the screen.

3. Create a method called `largest5`. This method must take 5 double parameters 42.0, 35.1, 2.3, 40.2, and 15.6 and return the largest. You must call the method and print the returned value to the screen.

## Exercise 4 Array Fun

### Requirements

The fourth exercise is called Array Fun and focuses on your understanding of arrays. Each of the items in this exercise must be contained in a separate Java file with a main method.

### Instructions

Follow the steps below to complete the Array Fun Exercise. The program must do the following:

1. This solution must be contained in a Java file called `ArrayFunUserArray`. Your code must read 5 ints from the user, put them in an array, and print the array elements to the screen.

2. This solution must be contained in a Java file called `ArrayFunReverselt`. Your code must declare the array [1, 2, 3, 4, 5], create another array of equal length, and fill that array with values from the original array but in reverse order. Finally, your code must print the contents of both arrays to the screen.

3. This solution must be contained in a Java file called `ArrayFun5Words`. Your code must get 5 words from the user, put them in an array, and print the array elements to the screen.

## Submission

Great job completing your first Challenge! Now it’s time to submit. It may seem intimidating to look at, but these steps will become second nature before you know it. Make sure to double check that your submission is completed.

1. **Copy your folder:** When you have completed your Challenge Assessment, copy your completed `U1-M1-Challenge-Lastname-Firstname` folder (located inside in your Working Folder Java) into your personal GitHub repository folder located on your desktop.

2.  Complete the commands:

    

   Once the `U1-M1-Challenge-Lastname-Firstname` folder has been copied into the GitHub repository folder, navigate into your GitHub repository in either your Terminal (Mac) or Git Bash (Windows). Once you have navigated to the appropriate repository, complete the following commands (which should be familiar if you have completed your Prework):

   - `git add -A`
   - `git commit-m"< commit message >" ` where `< commit message > ` is your commit message for the commit.
   - `git push origin master`. 

3. **Navigate:** Open your personal account on Github.com. Navigate to the  `YourFirstName_YourLastName_Java` repository.

4. **Confirm:** Click on the `U1-M1-Challenge-Lastname-Firstname` folder that you created and uploaded. Confirm that ALL of the expected projects have been included inside that folder. Again, the folder name should be related to the Unit and Module associated with the Challenge Assessment.

5. **Copy the URL:** Copy the URL of the Challenge folder from the browser address bar.

6. **Paste the URL:** Log into BootCampSpot and paste the URL for this specific Challenge in the appropriate location.

## Grading Requirements

In this challenge, your grade will consist of a combination of general setup and format requirements, your actual code, and code reviews. Each exercise is assessed based on the quality of your code and code review. 

Code review requires each student to meet with a member of the instructional staff one-on-one to go over the coding process used and to check for academic understanding. See the criteria below for more detailed information:

### General Set Up and format requirements: 20%

- All code must reside in one IntelliJ project called `U1-M1-Challenge-Lastname-Firstname`.
- Each exercise must contain its own Java file.
- Each Java file must contain a main method and must be named as directed in the exercise instructions.

### Exercise 1 Code: 20%

- Solution must consist of the following two files: 
  - `MonthConverterIf`
  - `MonthConverterSwitch`

- Program prompts the user to enter a number between 1 and 12.
- Program prints out the name of the month corresponding to the number typed in by the user.
- Program prints out the following error message if the user enters a number less than 1 or greater than 12: "You have entered an invalid number. You must enter a number between 1 and 12. Goodbye."

### Exercise 2 Code: 20%

- **Solution must be in a Java file called `ValidNumber`.**
- Program prompts the user to enter a number between 1 and 10.
- If the user enters a number less than 1 or greater than 10, the program displays the following error message and prompts the user for another entry: "You must enter a number between 1 and 10. Please try again."
- When the user enters a valid number, the program prints the number to the screen and exits.

### Exercise 3 Code: 20%

- **Your solution must be in a Java file called `SomeMath`.**
- Program contains a method called `total5`. This method must take 5 int parameters and return the total. Program must call the method with the following parameters and print the returned total to the screen: 1, 2, 3, 4, 5.
- Program contains a method called `average5`. This method must take 5 int parameters and return the average of the numbers as a double. Program must call the method with the following parameters and print the returned average to the screen: 1, 3, 5, 7, 9.
- Program contains a method called `largest5`. This method must take 5 double parameters and return the largest. Program must call the method with the following parameters and print the returned value to the screen: 42.0, 35.1, 2.3, 40.2, 15.6.

### Exercise 4 Code: 20%

- **Each of the items in this exercise must be contained in a separate Java file with a main method as specified below.**
- This solution must be contained in a Java file called `ArrayFunUserArray`. Code must read 5 ints from the user, put them in an array, and print the array elements to the screen.
- This solution must be contained in a Java file called `ArrayFunReverselt`. Code must declare the array [1, 2, 3, 4, 5], create another array of equal length, and fill that array with values from the original array but in reverse order. Code must print the contents of both arrays to the screen.
- This solution must be contained in a Java file called `ArrayFun5Words`. Code must get 5 words from the user, put them in an array, and print the array elements to the screen.

---

© 2021 Trilogy Education Services, a 2U, Inc. brand. All Rights Reserved.


