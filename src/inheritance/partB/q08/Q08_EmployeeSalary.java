package inheritance.partB.q08;

/*
 * QUESTION 8 — Override calculateSalary()
 * ---------------------------------------
 * Employee reference → FullTime / PartTime / Contract object.
 *
 * Same trick as Q7: the OBJECT decides which calculateSalary() runs.
 *
 * Simple rules used here:
 *   FullTime  : monthly salary
 *   PartTime  : hours * hourlyRate
 *   Contract  : fixed contract amount
 */

class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    double calculateSalary() {
        return 0;
    }
}

class FullTimeEmployee extends Employee {
    double monthly;

    FullTimeEmployee(String name, double monthly) {
        super(name);
        this.monthly = monthly;
    }

    @Override
    double calculateSalary() {
        return monthly;
    }
}

class PartTimeEmployee extends Employee {
    int hours;
    double hourlyRate;

    PartTimeEmployee(String name, int hours, double hourlyRate) {
        super(name);
        this.hours = hours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double calculateSalary() {
        return hours * hourlyRate;
    }
}

class ContractEmployee extends Employee {
    double contractAmount;

    ContractEmployee(String name, double contractAmount) {
        super(name);
        this.contractAmount = contractAmount;
    }

    @Override
    double calculateSalary() {
        return contractAmount;
    }
}

public class Q08_EmployeeSalary {
    public static void main(String[] args) {
        Employee e;

        e = new FullTimeEmployee("Meera", 40000);
        System.out.println(e.name + " (full time)  = " + e.calculateSalary());

        e = new PartTimeEmployee("Rohit", 80, 200);
        System.out.println(e.name + " (part time)  = " + e.calculateSalary());

        e = new ContractEmployee("Sana", 25000);
        System.out.println(e.name + " (contract)   = " + e.calculateSalary());
    }
}
