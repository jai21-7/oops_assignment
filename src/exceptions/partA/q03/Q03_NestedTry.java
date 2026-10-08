package exceptions.partA.q03;

/*
 * Nested try
 * ----------
 * A try block can sit INSIDE another try.
 * The inner catch handles the inner problem.
 * If inner does not catch it, the outer catch can still handle it.
 *
 * Inner: divide by zero
 * Outer: bad array index
 */

public class Q03_NestedTry {
    public static void main(String[] args) {
        try {
            int[] data = {10, 0};
            try {
                int q = data[0] / data[1];
                System.out.println("q = " + q);
            } catch (ArithmeticException e) {
                System.out.println("Inner catch: divide by zero.");
            }
            System.out.println("Now a bad index in the OUTER try...");
            System.out.println(data[9]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Outer catch: bad array index.");
        }
    }
}
