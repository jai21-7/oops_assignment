package partB.q09;

/*
 * QUESTION 9
 * ---------
 * Create a Rectangle class with OVERLOADED CONSTRUCTORS:
 *   - default constructor          (no arguments)
 *   - one value                    (a square: length = breadth)
 *   - length and breadth           (a real rectangle)
 * Then DISPLAY THE AREA.
 *
 * BEGINNER IDEA:
 *   Default constructor = Rectangle() with empty ().
 *   If you write ANY constructor, Java does NOT give you a default one
 *   automatically. So we write it ourselves.
 *
 *   Square is a special rectangle: both sides equal.
 *   So one number is enough: new Rectangle(5) means 5 x 5.
 *
 * AREA = length * breadth
 */

class Rectangle {
    double length;
    double breadth;

    // Default: we pick 1 x 1 so area is defined
    Rectangle() {
        length = 1;
        breadth = 1;
        System.out.println("Constructor: Rectangle()  [default 1 x 1]");
    }

    // Square: one side used twice
    Rectangle(double side) {
        length = side;
        breadth = side;
        System.out.println("Constructor: Rectangle(side)  [square]");
    }

    // Rectangle: two different sides
    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
        System.out.println("Constructor: Rectangle(length, breadth)");
    }

    double area() {
        return length * breadth;
    }

    void show() {
        System.out.println("Sides " + length + " x " + breadth + " | Area = " + area());
    }
}

public class Q09_RectangleConstructors {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();       // STEP 1: default
        r1.show();
        System.out.println();

        Rectangle r2 = new Rectangle(5);      // STEP 2: square 5 x 5
        r2.show();
        System.out.println();

        Rectangle r3 = new Rectangle(8, 3);   // STEP 3: 8 x 3
        r3.show();
    }
}
