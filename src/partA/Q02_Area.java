/*
 * QUESTION 2
 * ---------
 * Create an Area class with OVERLOADED calculateArea() methods for:
 *   - a square    (needs 1 side)
 *   - a rectangle (needs length and breadth)
 *   - a circle    (needs radius)
 *
 * BEGINNER IDEA:
 *   Area of square    = side * side
 *   Area of rectangle = length * breadth
 *   Area of circle    = PI * radius * radius
 *
 *   Same method NAME, different SHAPES, so different PARAMETERS.
 *   That is overloading: one name, many jobs, distinguished by arguments.
 */

class Area {

    // Square: only one measurement is needed
    double calculateArea(double side) {
        System.out.println("Called: calculateArea(side)  -> square");
        return side * side;
    }

    // Rectangle: two measurements
    double calculateArea(double length, double breadth) {
        System.out.println("Called: calculateArea(length, breadth)  -> rectangle");
        return length * breadth;
    }

    // Circle: we pass radius AND a flag/char so Java can tell this apart
    // from the square version (which also has ONE double).
    //
    // IMPORTANT BEGINNER NOTE:
    //   You CANNOT overload using ONLY the parameter NAME.
    //   calculateArea(double side) and calculateArea(double radius)
    //   look THE SAME to Java (one double). Names do not count.
    //
    //   So for a circle we add a second parameter: the shape name.
    //   Another common teaching style uses an extra unused parameter,
    //   but a String label is clearer for beginners.
    double calculateArea(double radius, String shape) {
        System.out.println("Called: calculateArea(radius, \"" + shape + "\")  -> circle");
        if (!shape.equalsIgnoreCase("circle")) {
            System.out.println("This version is meant for a circle.");
        }
        return Math.PI * radius * radius;
    }
}

public class Q02_Area {
    public static void main(String[] args) {
        Area area = new Area();

        // STEP 1: one number -> square formula
        double square = area.calculateArea(5);
        System.out.println("Square side 5, area = " + square);
        System.out.println();

        // STEP 2: two numbers -> rectangle formula
        double rectangle = area.calculateArea(8, 4);
        System.out.println("Rectangle 8 x 4, area = " + rectangle);
        System.out.println();

        // STEP 3: radius + the word "circle" -> circle formula
        double circle = area.calculateArea(7, "circle");
        System.out.println("Circle radius 7, area = " + circle);
    }
}
