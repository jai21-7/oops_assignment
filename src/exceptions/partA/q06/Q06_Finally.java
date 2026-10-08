package exceptions.partA.q06;

/*
 * finally
 * -------
 * Code in finally RUNS ALWAYS after try/catch:
 *   - if there was an exception
 *   - if there was NO exception
 *   - even if you return from try
 *
 * Typical use: close a file, unlock, print "cleanup".
 */

public class Q06_Finally {
    public static void main(String[] args) {
        try {
            System.out.println("try: 10 / 2 = " + (10 / 2));
        } catch (ArithmeticException e) {
            System.out.println("catch: " + e);
        } finally {
            System.out.println("finally: always runs after success.");
        }

        try {
            System.out.println("try: 10 / 0 = " + (10 / 0));
        } catch (ArithmeticException e) {
            System.out.println("catch: divide by zero.");
        } finally {
            System.out.println("finally: always runs after failure too.");
        }
    }
}
