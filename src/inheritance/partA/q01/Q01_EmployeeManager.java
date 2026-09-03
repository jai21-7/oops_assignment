package inheritance.partA.q01;

/*
 * QUESTION 1 — Inheritance
 * ------------------------
 * Superclass  : Employee  (name, salary, displayEmployee())
 * Subclass    : Manager   (department, displayManager())
 *
 * WHAT IS INHERITANCE? (beginner)
 *   A subclass REUSES the fields and methods of a superclass.
 *   Write:  class Manager extends Employee
 *
 *   Manager IS-A Employee, so a Manager already has name and salary.
 *   You only add what is extra: department.
 *
 *   "extends" = "get everything from the parent, then add more."
 *
 * STEPS
 *   1. Write Employee with name, salary, displayEmployee().
 *   2. Write Manager extends Employee, add department, displayManager().
 *   3. In displayManager(), first show employee details (reuse), then department.
 *   4. Create one Manager object and print everything.
 */

class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    void displayEmployee() {
        System.out.println("Name   : " + name);
        System.out.println("Salary : " + salary);
    }
}

class Manager extends Employee {
    String department;

    Manager(String name, double salary, String department) {
        super(name, salary); // call Employee constructor first
        this.department = department;
    }

    void displayManager() {
        displayEmployee(); // inherited method — no need to rewrite name/salary print
        System.out.println("Dept   : " + department);
    }
}

public class Q01_EmployeeManager {
    public static void main(String[] args) {
        Manager m = new Manager("Kavya", 75000, "Sales");
        m.displayManager();
    }
}
