package partD.q21;

/*
 * QUESTION 21  (COMBINED)
 * ---------
 * BankAccount:
 *   - constructor overloading (different ways to open an account)
 *   - transfer(BankAccount b, double amount)
 *       move money FROM this account TO account b
 *
 * BEGINNER STEPS FOR A TRANSFER:
 *   1. Check this account has enough money.
 *   2. Subtract amount from this.balance
 *   3. Add amount to b.balance
 *
 *   this = sender (the object before the dot)
 *   b    = receiver (the object passed in)
 *
 *   a1.transfer(a2, 500);  means a1 pays a2 five hundred.
 */

class BankAccount {
    int accountNo;
    String holder;
    double balance;

    BankAccount(int accountNo) {
        this(accountNo, "Unknown", 0);
    }

    BankAccount(int accountNo, String holder) {
        this(accountNo, holder, 0);
    }

    BankAccount(int accountNo, String holder, double balance) {
        this.accountNo = accountNo;
        this.holder = holder;
        this.balance = balance;
    }

    void transfer(BankAccount b, double amount) {
        System.out.println("Transfer " + amount + " from " + holder
                + " to " + b.holder);
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (balance < amount) {
            System.out.println("Failed: not enough balance.");
            return;
        }
        balance = balance - amount;
        b.balance = b.balance + amount;
        System.out.println("Transfer successful.");
    }

    void show() {
        System.out.println(holder + " (A/c " + accountNo + ") balance = " + balance);
    }
}

public class Q21_BankAccountTransfer {
    public static void main(String[] args) {
        BankAccount a1 = new BankAccount(1001, "Arjun", 2000);
        BankAccount a2 = new BankAccount(1002, "Diya", 500);

        System.out.println("Before:");
        a1.show();
        a2.show();
        System.out.println();

        a1.transfer(a2, 700);

        System.out.println();
        System.out.println("After:");
        a1.show();
        a2.show();

        System.out.println();
        a2.transfer(a1, 5000); // should fail
    }
}
