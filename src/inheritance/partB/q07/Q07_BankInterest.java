package inheritance.partB.q07;

/*
 * QUESTION 7 — Runtime polymorphism (dynamic method dispatch)
 * -----------------------------------------------------------
 * Bank.getRateOfInterest() is overridden in SBI, HDFC, ICICI.
 *
 * KEY LINE:
 *   Bank b = new SBI();
 *        ^          ^
 *   reference     actual object
 *   type = Bank   type = SBI
 *
 * For INSTANCE methods (not static):
 *   Java looks at the OBJECT (right side of new), not the reference.
 *   So b.getRateOfInterest() runs SBI's method.
 *
 * That decision happens WHEN THE PROGRAM RUNS = runtime polymorphism.
 *
 * STEPS
 *   1. Override getRateOfInterest() in each bank.
 *   2. Point a Bank reference at each subclass object.
 *   3. Call the method. The printed rate must match the object, not "Bank".
 */

class Bank {
    double getRateOfInterest() {
        return 0;
    }
}

class SBI extends Bank {
    @Override
    double getRateOfInterest() {
        return 6.5;
    }
}

class HDFC extends Bank {
    @Override
    double getRateOfInterest() {
        return 7.2;
    }
}

class ICICI extends Bank {
    @Override
    double getRateOfInterest() {
        return 7.0;
    }
}

public class Q07_BankInterest {
    public static void main(String[] args) {
        Bank b; // one reference, three different objects

        b = new SBI();
        System.out.println("SBI rate   : " + b.getRateOfInterest());

        b = new HDFC();
        System.out.println("HDFC rate  : " + b.getRateOfInterest());

        b = new ICICI();
        System.out.println("ICICI rate : " + b.getRateOfInterest());
    }
}
