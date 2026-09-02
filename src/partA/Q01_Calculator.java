/*
 * QUESTION 1
 * ---------
 * Create a Calculator class with OVERLOADED add() methods that can:
 *   - add two integers
 *   - add three integers
 *   - add two double (decimal) values
 *
 * WHAT IS METHOD OVERLOADING? (beginner)
 *   In Java, you can give several methods the SAME NAME in the same class,
 *   as long as their PARAMETER LISTS are different.
 *   Different can mean:
 *     1) different number of parameters  (2 ints vs 3 ints)
 *     2) different types of parameters   (int vs double)
 *
 *   The compiler looks at how you CALL the method and picks the matching version.
 *   This is also called "compile-time polymorphism".
 *
 * WHY IS THIS USEFUL?
 *   The programmer remembers one name: add().
 *   Java figures out which add() to run based on the arguments you pass.
 */

class Calculator {

    // Version 1: two integers
    int add(int a, int b) {
        System.out.println("Called: add(int, int)");
        return a + b;
    }

    // Version 2: three integers  (different NUMBER of parameters)
    int add(int a, int b, int c) {
        System.out.println("Called: add(int, int, int)");
        return a + b + c;
    }

    // Version 3: two doubles     (different TYPE of parameters)
    double add(double a, double b) {
        System.out.println("Called: add(double, double)");
        return a + b;
    }
}

public class Q01_Calculator {
    public static void main(String[] args) {
        Calculator calc = new Calculator(); // create one calculator object

        // STEP 1: two ints  -> Java matches add(int, int)
        int sum2 = calc.add(10, 20);
        System.out.println("10 + 20 = " + sum2);
        System.out.println();

        // STEP 2: three ints -> Java matches add(int, int, int)
        int sum3 = calc.add(5, 15, 25);
        System.out.println("5 + 15 + 25 = " + sum3);
        System.out.println();

        // STEP 3: two doubles -> Java matches add(double, double)
        //  Writing 12.5 (with a decimal) tells Java these are doubles, not ints.
        double sumD = calc.add(12.5, 7.3);
        System.out.println("12.5 + 7.3 = " + sumD);
    }
}
