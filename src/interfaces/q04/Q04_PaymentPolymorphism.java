package interfaces.q04;

/*
 * Interface as a TYPE (polymorphism)
 * ----------------------------------
 * You can write:  Payment p = new UpiPayment();
 * Same as a superclass reference, but the "parent" is an interface.
 *
 * Useful: a shop can accept ANY Payment without knowing the exact class.
 */

interface Payment {
    void pay(double amount);
}

class CardPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " by card");
    }
}

class UpiPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " by UPI");
    }
}

class CashPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " in cash");
    }
}

public class Q04_PaymentPolymorphism {
    static void checkout(Payment p, double amount) {
        p.pay(amount); // whichever object was passed
    }

    public static void main(String[] args) {
        checkout(new CardPayment(), 499);
        checkout(new UpiPayment(), 149);
        checkout(new CashPayment(), 50);
    }
}
