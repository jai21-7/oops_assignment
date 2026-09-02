package partB.q10;

/*
 * QUESTION 10
 * ---------
 * Create a Box class with OVERLOADED CONSTRUCTORS:
 *   - no dimensions
 *   - one dimension          (a cube: length = width = height)
 *   - length, width, height
 * Then CALCULATE AND DISPLAY THE VOLUME.
 *
 * BEGINNER IDEA:
 *   Volume of a box = length * width * height
 *   A cube is a box whose three sides are equal.
 *   No dimensions: we use 0 (empty box) so volume is 0.
 */

class Box {
    double length;
    double width;
    double height;

    Box() {
        length = 0;
        width = 0;
        height = 0;
        System.out.println("Constructor: Box()  [no dimensions]");
    }

    Box(double side) {
        length = side;
        width = side;
        height = side;
        System.out.println("Constructor: Box(side)  [cube]");
    }

    Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
        System.out.println("Constructor: Box(length, width, height)");
    }

    double volume() {
        return length * width * height;
    }

    void show() {
        System.out.println("Box " + length + " x " + width + " x " + height
                + " | Volume = " + volume());
    }
}

public class Q10_BoxConstructors {
    public static void main(String[] args) {
        Box b1 = new Box();
        b1.show();
        System.out.println();

        Box b2 = new Box(4);              // cube 4 x 4 x 4 = 64
        b2.show();
        System.out.println();

        Box b3 = new Box(2, 3, 5);        // 2*3*5 = 30
        b3.show();
    }
}
