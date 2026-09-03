package inheritance.partC.q14;

/*
 * QUESTION 14 — work() vs companyPolicy()
 * ---------------------------------------
 * Employee → Manager
 *
 *   void work()                 instance  → Manager OVERRIDES it
 *   static void companyPolicy() static    → Manager HIDES it
 *
 * Employee e = new Manager();
 * Manager  m = new Manager();
 *
 * e.work()           → Manager work          (object)
 * m.work()           → Manager work          (object)
 * e.companyPolicy()  → Employee policy       (reference Employee)
 * m.companyPolicy()  → Manager policy        (reference Manager)
 *
 * WHY they differ:
 *   work() is an instance method, bound at RUNTIME to the object.
 *   companyPolicy() is static, bound at COMPILE TIME to the reference type.
 */

class Employee {
    void work() {
        System.out.println("Employee works on assigned tasks");
    }

    static void companyPolicy() {
        System.out.println("Employee policy: 9 to 5, follow handbook");
    }
}

class Manager extends Employee {
    @Override
    void work() {
        System.out.println("Manager plans, reviews, and leads the team");
    }

    static void companyPolicy() {
        System.out.println("Manager policy: extra meeting hours, team reviews");
    }
}

public class Q14_EmployeeManagerHide {
    public static void main(String[] args) {
        Employee e = new Manager();
        Manager m = new Manager();

        System.out.println("e.work() / m.work()   [instance = overriding]");
        e.work();
        m.work();

        System.out.println();
        System.out.println("e.companyPolicy() / m.companyPolicy()  [static = hiding]");
        e.companyPolicy();
        m.companyPolicy();
    }
}
