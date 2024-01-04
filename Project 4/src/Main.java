//Import Section

/*
 * Provided in this class is the neccessary code to get started with your game's implementation
 * You will find a while loop that should take your minefield's gameOver() method as its conditional
 * Then you will prompt the user with input and manipulate the data as before in project 2
 * 
 * Things to Note:
 * 1. Think back to project 1 when we asked our user to give a shape. In this project we will be asking the user to provide a mode. Then create a minefield accordingly
 * 2. You must implement a way to check if we are playing in debug mode or not.
 * 3. When working inside your while loop think about what happens each turn. We get input, user our methods, check their return values. repeat.
 * 4. Once while loop is complete figure out how to determine if the user won or lost. Print appropriate statement.
 */

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for game mode
        System.out.println("Welcome to Minesweeper!");
        System.out.println("Choose a difficulty level by an integer between 1-3:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");

        int difficultyChoice = scanner.nextInt();

        int rows;
        int columns;
        int mines;
        int flags;
//        For whatever difficulty you input the rows flags and columns are set for the field
        if (difficultyChoice == 1) {
            rows = 5;
            columns = 5;
            mines = 5;
            flags = 5;
        } else if (difficultyChoice == 2) {
            rows = 9;
            columns = 9;
            mines = 12;
            flags = 12;
        } else if (difficultyChoice == 3) {
            rows = 20;
            columns = 20;
            mines = 40;
            flags = 40;
        } else {
            System.out.println("Invalid choice. Exiting the game.");
            return;
        }
        Minefield minefield = new Minefield(rows, columns, flags);
        System.out.println("Do you want to play debug mode? [true or false]");
        boolean gameChoice = scanner.nextBoolean();
        System.out.println("Enter your move as an integer between 0 and " + rows+ " (x):");
        int x = scanner.nextInt();// Set up the minefield row
        while ((0 >= x) && (x >= rows)){// Keeps going until a valid input is provided
            System.out.println("Reenter your coordinates");
            x = scanner.nextInt();
        }
        System.out.println("Enter your move as an integer between 0 and " + columns+ " (y):");
        int y = scanner.nextInt(); // Set up the minefield column
        while ((0 >= y) && (y >= columns)){// Keeps going until a valid input is provided
            System.out.println("Reenter your coordinates");
            x = scanner.nextInt();
        }
        // Initialize the field with mines, evaluate the field, and reveal surrounding areas
        minefield.createMines(x, y, mines);
        minefield.evaluateField();
        minefield.revealZeroes(x, y);
        minefield.revealStartingArea(x, y);

        while (!minefield.gameOver()) {
            if (gameChoice){
                minefield.debug();
            }
            System.out.println("Current Minefield:");
            System.out.println(minefield.toString());

            int x1;
            int y1;
            boolean flag;

            try {
                System.out.println("Enter your move (x)");
                x1 = scanner.nextInt();

                System.out.println("Enter your move (y)");
                y1 = scanner.nextInt();

                System.out.println("Enter your move flag (true or false), Flags remaining: " + minefield.getFlags() + "):");
                flag = scanner.nextBoolean();
                // Check if the flag is used correctly and within bounds
                if (flag && flags <= 0) {
                    System.out.println("No more flags. Try again.");
                }
//              check that the inputed coordinates are inbounds and the flag is true and false
                if ((0 <= x1) && (x1 <= rows) && (0 <= y1) && (y1 <= columns) && (flag == true || flag == false)) {

                    boolean hit = minefield.guess(x1, y1, flag);
                    if (hit) {// Check if the move resulted in hitting a mine
                        System.out.println("Game over! You hit a mine.");
                        break;
                    }
                } else {
                    System.out.println("Invalid move. Please try again.");
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer or boolean.");

            }
        }


        // Game over; display results
        if (minefield.gameOver()) {
            System.out.println(minefield.toString());
            System.out.println("Congratulations! You've cleared the minefield!");
        }
        scanner.close();
    }
}