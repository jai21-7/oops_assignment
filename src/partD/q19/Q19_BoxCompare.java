package partD.q19;

/*
 * QUESTION 19  (COMBINED)
 * ---------
 * Box with constructor overloading
 * AND compare(Box b) that compares VOLUMES of two Box objects.
 *
 * THIS MIXES PART B + PART C:
 *   - several constructors  (how you CREATE boxes)
 *   - object as parameter   (how you COMPARE two boxes)
 *
 * Volume = length * width * height
 */

class Box {
    double length;
    double width;
    double height;

    Box() {
        this(1, 1, 1); // chaining: default is a 1x1x1 cube
    }

    Box(double side) {
        this(side, side, side); // cube
    }

    Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    double volume() {
        return length * width * height;
    }

    void compare(Box b) {
        double mine = this.volume();
        double other = b.volume();
        System.out.println("This box volume  : " + mine);
        System.out.println("Other box volume : " + other);
        if (mine > other) {
            System.out.println("Current box is larger.");
        } else if (mine < other) {
            System.out.println("Other box is larger.");
        } else {
            System.out.println("Both boxes have equal volume.");
        }
    }
}

public class Q19_BoxCompare {
    public static void main(String[] args) {
        Box defaultBox = new Box();          // 1x1x1 = 1
        Box cube = new Box(3);               // 27
        Box carton = new Box(2, 4, 5);       // 40

        System.out.println("Default vs cube:");
        defaultBox.compare(cube);
        System.out.println();
        System.out.println("Cube vs carton:");
        cube.compare(carton);
    }
}
