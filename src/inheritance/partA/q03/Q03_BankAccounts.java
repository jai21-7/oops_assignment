package inheritance.partA.q03;

/*
 * QUESTION 3 — Inheritance with extra features in subclasses
 * ----------------------------------------------------------
 * Superclass : BankAccount (accountNumber, holderName, balance)
 *              methods: deposit(), withdraw(), displayBalance()
 * Subclass   : SavingsAccount  extra feature = interest
 * Subclass   : CurrentAccount  extra feature = overdraft limit
 *
 * STEPS
 *   1. Put shared banking behaviour in BankAccount.
 *   2. SavingsAccount adds addInterest() using an interest rate.
 *   3. CurrentAccount allows withdraw() a bit beyond balance (overdraft).
 *   4. Create one object of each and show the extra feature.
 */

class BankAccount {
    int accountNumber;
    String holderName;
    double balance;

    BankAccount(int accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Deposited " + amount);
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            System.out.println("Withdrew " + amount);
        } else {
            System.out.println("Withdraw failed.");
        }
    }

    void displayBalance() {
        System.out.println(holderName + " (A/c " + accountNumber + ") balance = " + balance);
    }
}

class SavingsAccount extends BankAccount {
    double interestRate; // extra feature

    SavingsAccount(int accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }

    void addInterest() {
        double interest = balance * interestRate / 100;
        balance = balance + interest;
        System.out.println("Interest added: " + interest);
    }
}

class CurrentAccount extends BankAccount {
    double overdraftLimit; // extra feature: can go a little negative

    CurrentAccount(int accountNumber, String holderName, double balance, double overdraftLimit) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    // Savings uses the parent withdraw(). Current needs its own rule.
    void withdraw(double amount) {
        if (amount > 0 && amount <= balance + overdraftLimit) {
            balance = balance - amount;
            System.out.println("Withdrew " + amount + " (overdraft allowed up to " + overdraftLimit + ")");
        } else {
            System.out.println("Withdraw failed: exceeds overdraft.");
        }
    }
}

public class Q03_BankAccounts {
    public static void main(String[] args) {
        SavingsAccount sav = new SavingsAccount(101, "Neha", 10000, 5);
        System.out.println("=== Savings ===");
        sav.displayBalance();
        sav.addInterest();
        sav.displayBalance();

        System.out.println();
        CurrentAccount cur = new CurrentAccount(202, "Arjun", 2000, 1000);
        System.out.println("=== Current ===");
        cur.displayBalance();
        cur.withdraw(2500); // 500 into overdraft — allowed
        cur.displayBalance();
        cur.withdraw(2000); // would go beyond overdraft — fail
    }
}
