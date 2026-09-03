package inheritance.partB.q09;

/*
 * QUESTION 9 — Override makePayment()
 * -----------------------------------
 * Payment p; then p = new CreditCardPayment() / UPI / NetBanking.
 *
 * Each subclass prints its own payment style.
 * The reference type is always Payment; the method that runs
 * still belongs to the REAL object.
 */

class Payment {
    void makePayment() {
        System.out.println("Generic payment");
    }
}

class CreditCardPayment extends Payment {
    @Override
    void makePayment() {
        System.out.println("Paid using Credit Card");
    }
}

class UPIPayment extends Payment {
    @Override
    void makePayment() {
        System.out.println("Paid using UPI");
    }
}

class NetBankingPayment extends Payment {
    @Override
    void makePayment() {
        System.out.println("Paid using Net Banking");
    }
}

public class Q09_Payments {
    public static void main(String[] args) {
        Payment p;

        p = new CreditCardPayment();
        p.makePayment();

        p = new UPIPayment();
        p.makePayment();

        p = new NetBankingPayment();
        p.makePayment();
    }
}
