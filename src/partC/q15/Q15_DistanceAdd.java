package partC.q15;

/*
 * QUESTION 15
 * ---------
 * Distance has feet and inches.
 * add(Distance d) takes another Distance and RETURNS a NEW Distance
 * that is their sum.
 *
 * BEGINNER IDEA:
 *   1 foot = 12 inches.
 *   If inches become 12 or more, convert extra inches into feet.
 *
 *   Example:
 *     5 feet 8 inches
 *   + 3 feet 10 inches
 *   = 8 feet 18 inches  -> 18 inches is 1 foot 6 inches
 *   = 9 feet 6 inches
 *
 * RETURNING AN OBJECT:
 *   The method does not print the answer only.
 *   It builds a new Distance object and gives it back:
 *     Distance total = d1.add(d2);
 */

class Distance {
    int feet;
    int inches;

    Distance(int feet, int inches) {
        this.feet = feet;
        this.inches = inches;
        normalize(); // always keep inches < 12
    }

    // If inches >= 12, move extra into feet.
    void normalize() {
        if (inches >= 12) {
            feet = feet + inches / 12;
            inches = inches % 12;
        }
    }

    Distance add(Distance d) {
        int totalFeet = this.feet + d.feet;
        int totalInches = this.inches + d.inches;
        return new Distance(totalFeet, totalInches); // constructor will normalize
    }

    void show(String label) {
        System.out.println(label + ": " + feet + " feet " + inches + " inches");
    }
}

public class Q15_DistanceAdd {
    public static void main(String[] args) {
        Distance d1 = new Distance(5, 8);
        Distance d2 = new Distance(3, 10);

        d1.show("First");
        d2.show("Second");

        // STEP: add returns a NEW object; d1 and d2 stay unchanged
        Distance sum = d1.add(d2);
        sum.show("Sum");
    }
}
