/*
 * QUESTION 3
 * ---------
 * Create a Maximum class with OVERLOADED max() methods to find:
 *   - the larger of TWO integers
 *   - the largest of THREE integers
 *   - the larger of TWO doubles
 *
 * BEGINNER IDEA:
 *   "max" means "which value is bigger?"
 *   We write three versions because the INPUTS are not always the same.
 *
 * HOW TO COMPARE:
 *   if (a > b) a is larger, else b is larger.
 *   For three numbers, compare the first two, then compare that winner with the third.
 */

class Maximum {

    int max(int a, int b) {
        System.out.println("Called: max(int, int)");
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    int max(int a, int b, int c) {
        System.out.println("Called: max(int, int, int)");
        int biggerOfFirstTwo = max(a, b); // reuse the 2-int version
        return max(biggerOfFirstTwo, c);
    }

    double max(double a, double b) {
        System.out.println("Called: max(double, double)");
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
}

public class Q03_Maximum {
    public static void main(String[] args) {
        Maximum m = new Maximum();

        // STEP 1: two ints
        System.out.println("max(12, 45) = " + m.max(12, 45));
        System.out.println();

        // STEP 2: three ints
        System.out.println("max(12, 45, 30) = " + m.max(12, 45, 30));
        System.out.println();

        // STEP 3: two doubles
        System.out.println("max(9.8, 9.81) = " + m.max(9.8, 9.81));
    }
}
