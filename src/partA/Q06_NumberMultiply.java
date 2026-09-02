/*
 * QUESTION 6
 * ---------
 * Create a Number class with OVERLOADED multiply() methods for:
 *   - two integers
 *   - three integers
 *   - two doubles
 *
 * BEGINNER IDEA:
 *   Same pattern as Question 1 (add), but the operation is multiplication.
 *   Practice seeing the PATTERN of overloading, not only the math.
 *
 *   Pattern:
 *     same name
 *     + different number of parameters  OR  different types
 *     = overloading
 */

class Number {

    int multiply(int a, int b) {
        System.out.println("Called: multiply(int, int)");
        return a * b;
    }

    int multiply(int a, int b, int c) {
        System.out.println("Called: multiply(int, int, int)");
        return a * b * c;
    }

    double multiply(double a, double b) {
        System.out.println("Called: multiply(double, double)");
        return a * b;
    }
}

public class Q06_NumberMultiply {
    public static void main(String[] args) {
        Number n = new Number();

        // STEP 1: 6 * 7
        System.out.println("6 * 7 = " + n.multiply(6, 7));
        System.out.println();

        // STEP 2: 2 * 3 * 4
        System.out.println("2 * 3 * 4 = " + n.multiply(2, 3, 4));
        System.out.println();

        // STEP 3: 1.5 * 2.5
        System.out.println("1.5 * 2.5 = " + n.multiply(1.5, 2.5));
    }
}
