package exceptions.partA.q04;

/*
 * throw  (note: not throws)
 * ------------------------
 * throw = YOU create and throw an exception object RIGHT NOW.
 *
 *   throw new IllegalArgumentException("marks cannot be negative");
 *
 * Use it when YOUR rule is broken (negative marks, empty name...),
 * even if Java itself would not crash.
 */

public class Q04_ThrowKeyword {
    static void checkMarks(int marks) {
        if (marks < 0) {
            throw new IllegalArgumentException("marks cannot be negative: " + marks);
        }
        System.out.println("Marks accepted: " + marks);
    }

    public static void main(String[] args) {
        try {
            checkMarks(85);
            checkMarks(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
