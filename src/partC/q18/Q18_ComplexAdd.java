package partC.q18;

/*
 * QUESTION 18
 * ---------
 * Complex number = real part + imaginary part.
 * Written as:  a + bi     (i is the imaginary unit, i^2 = -1)
 *
 * Example:  3 + 4i
 *           real = 3, imaginary = 4
 *
 * HOW TO ADD TWO COMPLEX NUMBERS:
 *   Add reals together, add imaginaries together.
 *   (3 + 4i) + (1 + 2i) = (3+1) + (4+2)i = 4 + 6i
 *
 * add(Complex c) takes another Complex object and returns the sum.
 */

class Complex {
    double real;
    double imag;

    Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    Complex add(Complex c) {
        double r = this.real + c.real;
        double i = this.imag + c.imag;
        return new Complex(r, i);
    }

    void show(String label) {
        // print nicely: 3.0 + 4.0i
        String sign = imag >= 0 ? " + " : " - ";
        System.out.println(label + ": " + real + sign + Math.abs(imag) + "i");
    }
}

public class Q18_ComplexAdd {
    public static void main(String[] args) {
        Complex c1 = new Complex(3, 4);
        Complex c2 = new Complex(1, 2);

        c1.show("First");
        c2.show("Second");

        Complex sum = c1.add(c2);
        sum.show("Sum");
    }
}
