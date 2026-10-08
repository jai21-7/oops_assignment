package exceptions.partA.q01;

/*
 * LECTURE: Exception Handling
 * QUESTION: try + catch for a built-in exception
 *
 * WHAT IS AN EXCEPTION? (beginner)
 *   An exception is an ERROR OBJECT Java creates when something goes wrong
 *   at RUN TIME (divide by zero, missing file, bad array index...).
 *   If you do not handle it, the program CRASHES.
 *
 * try    = "try this risky code"
 * catch  = "if that error happens, run THIS instead of crashing"
 *
 * STEPS
 *   1. Put 10 / 0 inside try.
 *   2. Catch ArithmeticException.
 *   3. Print a friendly message. The program continues after catch.
 */

public class Q01_TryCatch {
    public static void main(String[] args) {
        try {
            int a = 10;
            int b = 0;
            int result = a / b; // Java throws ArithmeticException here
            System.out.println("Result = " + result); // never reached
        } catch (ArithmeticException e) {
            System.out.println("Caught: cannot divide by zero.");
            System.out.println("Java message: " + e.getMessage());
        }
        System.out.println("Program did NOT crash. It continued.");
    }
}
