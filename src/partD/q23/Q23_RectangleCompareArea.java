package partD.q23;

/*
 * QUESTION 23  (COMBINED)
 * ---------
 * Rectangle with overloaded constructors
 * AND compareArea(Rectangle r).
 *
 * Mix of Q9 (constructors + area) and Q13-style comparison.
 */

class Rectangle {
    double length;
    double breadth;

    Rectangle() {
        this(1, 1);
    }

    Rectangle(double side) {
        this(side, side);
    }

    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    double area() {
        return length * breadth;
    }

    void compareArea(Rectangle r) {
        double a1 = this.area();
        double a2 = r.area();
        System.out.println("This area  : " + a1);
        System.out.println("Other area : " + a2);
        if (a1 > a2) {
            System.out.println("Current rectangle is larger.");
        } else if (a1 < a2) {
            System.out.println("Other rectangle is larger.");
        } else {
            System.out.println("Areas are equal.");
        }
    }
}

public class Q23_RectangleCompareArea {
    public static void main(String[] args) {
        Rectangle square = new Rectangle(6);     // 36
        Rectangle door = new Rectangle(3, 8);    // 24

        square.compareArea(door);
    }
}
