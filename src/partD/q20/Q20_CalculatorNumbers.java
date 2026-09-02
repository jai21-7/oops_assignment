package partD.q20;

/*
 * QUESTION 20  (COMBINED)
 * ---------
 * Calculator with overloaded add() for:
 *   - integers
 *   - doubles
 *   - TWO Number OBJECTS
 *
 * NEW PIECE:
 *   add(Number a, Number b) does not add raw ints.
 *   It reads a.value and b.value from the objects you pass.
 *
 * This is method overloading + object as parameter together.
 */

class Number {
    int value;

    Number(int value) {
        this.value = value;
    }
}

class Calculator {

    int add(int a, int b) {
        System.out.println("add(int, int)");
        return a + b;
    }

    double add(double a, double b) {
        System.out.println("add(double, double)");
        return a + b;
    }

    int add(Number a, Number b) {
        System.out.println("add(Number, Number)  -> using the values inside objects");
        return a.value + b.value;
    }
}

public class Q20_CalculatorNumbers {
    public static void main(String[] args) {
        Calculator c = new Calculator();

        System.out.println("Result = " + c.add(4, 6));
        System.out.println("Result = " + c.add(2.5, 3.5));

        Number n1 = new Number(10);
        Number n2 = new Number(15);
        System.out.println("Result = " + c.add(n1, n2));
    }
}
