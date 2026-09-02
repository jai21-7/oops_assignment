package partB.q08;

/*
 * QUESTION 8
 * ---------
 * Create an Employee class with OVERLOADED CONSTRUCTORS for:
 *   - employee ID only
 *   - employee ID and name
 *   - employee ID, name, and salary
 *
 * BEGINNER IDEA:
 *   Same pattern as Q7, different real-life fields.
 *   Practice: one class, three ways to CREATE the object.
 *
 *   new Employee(101)
 *   new Employee(101, "Karan")
 *   new Employee(101, "Karan", 45000.0)
 */

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id) {
        this.id = id;
        this.name = "Not assigned";
        this.salary = 0;
        System.out.println("Constructor: Employee(id)");
    }

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.salary = 0;
        System.out.println("Constructor: Employee(id, name)");
    }

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        System.out.println("Constructor: Employee(id, name, salary)");
    }

    void show() {
        System.out.println("ID : " + id + " | Name : " + name + " | Salary : " + salary);
    }
}

public class Q08_EmployeeConstructors {
    public static void main(String[] args) {
        Employee e1 = new Employee(101);
        e1.show();
        System.out.println();

        Employee e2 = new Employee(102, "Karan");
        e2.show();
        System.out.println();

        Employee e3 = new Employee(103, "Meera", 45000.0);
        e3.show();
    }
}
