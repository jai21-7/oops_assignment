package partC.q16;

/*
 * QUESTION 16
 * ---------
 * Number holds an integer.
 * add(Number n) adds the other Number's value TO THE CURRENT OBJECT.
 *
 * BEGINNER IDEA — this is DIFFERENT from Q15:
 *   Q15 returned a NEW object (originals unchanged).
 *   Q16 CHANGES this object (this.value grows).
 *
 *   n1 starts as 10
 *   n1.add(n2) where n2 is 7
 *   n1 is now 17
 *   n2 is still 7
 */

class Number {
    int value;

    Number(int value) {
        this.value = value;
    }

    void add(Number n) {
        System.out.println("Adding " + n.value + " into current value " + this.value);
        this.value = this.value + n.value;
    }

    void show(String label) {
        System.out.println(label + " = " + value);
    }
}

public class Q16_NumberAdd {
    public static void main(String[] args) {
        Number n1 = new Number(10);
        Number n2 = new Number(7);

        n1.show("n1 before");
        n2.show("n2 before");

        // STEP: n1 changes, n2 does not
        n1.add(n2);

        n1.show("n1 after");
        n2.show("n2 after (unchanged)");
    }
}
