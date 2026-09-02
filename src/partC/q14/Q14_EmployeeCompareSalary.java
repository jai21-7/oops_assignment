package partC.q14;

/*
 * QUESTION 14
 * ---------
 * Employee has ID, name, salary.
 * compareSalary(Employee e) compares salaries of two Employee objects.
 *
 * BEGINNER IDEA:
 *   Same idea as Q13, different fields.
 *   Calling object = this
 *   Parameter object = e
 */

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    void compareSalary(Employee e) {
        System.out.println(this.name + " earns " + this.salary
                + " | " + e.name + " earns " + e.salary);

        if (this.salary > e.salary) {
            System.out.println(this.name + " has the higher salary.");
        } else if (this.salary < e.salary) {
            System.out.println(e.name + " has the higher salary.");
        } else {
            System.out.println("Both employees earn the same.");
        }
    }
}

public class Q14_EmployeeCompareSalary {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Ravi", 35000);
        Employee e2 = new Employee(2, "Sana", 42000);

        // STEP: pass the second employee into the first employee's method
        e1.compareSalary(e2);
    }
}
