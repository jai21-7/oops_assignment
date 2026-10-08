package exceptions.partA.q07;

/*
 * User-defined exception
 * ----------------------
 * Java's exceptions are classes. You can write YOUR OWN
 * by extending Exception (checked) or RuntimeException (unchecked).
 *
 * Common lab: input name + age; if age is negative, throw your exception.
 *
 * Checked (extends Exception): caller MUST catch or declare throws.
 */

class NegativeAgeException extends Exception {
    NegativeAgeException(String message) {
        super(message); // store the message inside Exception
    }
}

public class Q07_NegativeAgeException {
    static void register(String name, int age) throws NegativeAgeException {
        if (age < 0) {
            throw new NegativeAgeException("Age cannot be negative for " + name + ": " + age);
        }
        System.out.println("Registered " + name + ", age " + age);
    }

    public static void main(String[] args) {
        try {
            register("Aisha", 20);
            register("Rohan", -3);
        } catch (NegativeAgeException e) {
            System.out.println("Custom exception caught: " + e.getMessage());
        }
    }
}
