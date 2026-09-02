package partA.q05;

/*
 * QUESTION 5
 * ---------
 * Create a Printer class with OVERLOADED print() methods for:
 *   - an integer
 *   - a double
 *   - a character
 *   - a String
 *
 * BEGINNER IDEA:
 *   System.out.println() itself is overloaded in Java!
 *   You can println an int, a double, a char, a String...
 *   This question asks you to build a tiny version of that idea.
 *
 *   Java chooses the method by looking at the TYPE of the argument:
 *     10      -> int
 *     3.14    -> double
 *     'A'     -> char   (single quotes)
 *     "Hello" -> String (double quotes)
 */

class Printer {

    void print(int value) {
        System.out.println("print(int)    -> " + value);
    }

    void print(double value) {
        System.out.println("print(double) -> " + value);
    }

    void print(char value) {
        System.out.println("print(char)   -> " + value);
    }

    void print(String value) {
        System.out.println("print(String) -> " + value);
    }
}

public class Q05_Printer {
    public static void main(String[] args) {
        Printer p = new Printer();

        // STEP 1 to 4: same method name, four different types
        p.print(42);
        p.print(3.14159);
        p.print('J');
        p.print("Hello Java");
    }
}
