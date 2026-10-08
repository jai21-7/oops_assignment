package interfaces.q06;

/*
 * Real-life: Shape interface with area()
 * Circle and Rectangle implement it.
 * We store them in a Shape[] and print each area.
 */

interface Shape {
    double area();

    String name();
}

class Circle implements Shape {
    double r;

    Circle(double r) {
        this.r = r;
    }

    public double area() {
        return Math.PI * r * r;
    }

    public String name() {
        return "Circle r=" + r;
    }
}

class Rectangle implements Shape {
    double l, b;

    Rectangle(double l, double b) {
        this.l = l;
        this.b = b;
    }

    public double area() {
        return l * b;
    }

    public String name() {
        return "Rectangle " + l + "x" + b;
    }
}

public class Q06_ShapeInterface {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(3), new Rectangle(4, 5) };
        for (Shape s : shapes) {
            System.out.println(s.name() + " area = " + s.area());
        }
    }
}
