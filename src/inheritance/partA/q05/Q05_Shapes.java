package inheritance.partA.q05;

/*
 * QUESTION 5 — Hierarchical inheritance
 * -------------------------------------
 * Shape
 *   ├── Circle     area = π r r
 *   ├── Rectangle  area = length * breadth
 *   └── Triangle   area = (1/2) * base * height
 *
 * Superclass has display().
 * Each subclass stores its own measurements and calculates area.
 *
 * HIERARCHICAL = one parent, several children (same idea as Q2).
 */

class Shape {
    String name;

    Shape(String name) {
        this.name = name;
    }

    void display() {
        System.out.println("Shape: " + name);
    }
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double length;
    double breadth;

    Rectangle(double length, double breadth) {
        super("Rectangle");
        this.length = length;
        this.breadth = breadth;
    }

    double area() {
        return length * breadth;
    }
}

class Triangle extends Shape {
    double base;
    double height;

    Triangle(double base, double height) {
        super("Triangle");
        this.base = base;
        this.height = height;
    }

    double area() {
        return 0.5 * base * height;
    }
}

public class Q05_Shapes {
    public static void main(String[] args) {
        Circle c = new Circle(7);
        Rectangle r = new Rectangle(8, 4);
        Triangle t = new Triangle(6, 5);

        c.display();
        System.out.println("Area = " + c.area());
        System.out.println();

        r.display();
        System.out.println("Area = " + r.area());
        System.out.println();

        t.display();
        System.out.println("Area = " + t.area());
    }
}
