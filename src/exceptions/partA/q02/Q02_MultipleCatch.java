package exceptions.partA.q02;

/*
 * Multiple catch blocks
 * --------------------
 * One try can be followed by SEVERAL catch blocks.
 * Java runs the FIRST catch whose type matches the exception.
 *
 * Put the MORE SPECIFIC type first (ArrayIndexOutOfBoundsException),
 * then a wider type (Exception). If Exception is first, it swallows everything
 * and the later catches become unreachable (compiler error).
 *
 * This demo hits an invalid array index.
 */

public class Q02_MultipleCatch {
    public static void main(String[] args) {
        int[] marks = {80, 70, 90};
        try {
            System.out.println("marks[0] = " + marks[0]);
            System.out.println("marks[5] = " + marks[5]); // invalid index
        } catch (ArithmeticException e) {
            System.out.println("Math error: " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught array error: index 5 does not exist.");
        } catch (Exception e) {
            System.out.println("Some other error: " + e);
        }
    }
}
