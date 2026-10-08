package exceptions.partA.q05;

/*
 * throws  (in the METHOD SIGNATURE)
 * ---------------------------------
 * throws = this method might produce that exception; CALLER must handle it.
 *
 * throw  = actually throw it now
 * throws = declare it on the method
 *
 * Integer.parseInt("abc") throws NumberFormatException.
 * We declare throws Exception so main must use try/catch
 * (or also declare throws).
 */

public class Q05_ThrowsKeyword {
    static int parseAge(String text) throws NumberFormatException {
        return Integer.parseInt(text); // may fail
    }

    public static void main(String[] args) {
        try {
            System.out.println("Age = " + parseAge("19"));
            System.out.println("Age = " + parseAge("nineteen"));
        } catch (NumberFormatException e) {
            System.out.println("Caught: \"" + e.getMessage() + "\" is not a number.");
        }
    }
}
