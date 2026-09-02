package partB.q07;

/*
 * QUESTION 7
 * ---------
 * Create a Student class with OVERLOADED CONSTRUCTORS to initialize:
 *   - only the name
 *   - name and roll number
 *   - name, roll number, and marks
 *
 * WHAT IS A CONSTRUCTOR? (beginner)
 *   A constructor is a special method that RUNS WHEN YOU CREATE AN OBJECT.
 *     Student s = new Student("Riya");
 *   The part after `new` is a constructor call.
 *
 * RULES:
 *   - Constructor name = class name
 *   - No return type (not even void)
 *
 * WHAT IS CONSTRUCTOR OVERLOADING?
 *   Several constructors in the same class, different parameter lists.
 *   You pick which one by how you write `new Student(...)`.
 *
 * DEFAULT VALUES:
 *   If a field is not given, we store a sensible placeholder
 *   (roll = 0, marks = 0) so the object is still usable.
 */

class Student {
    String name;
    int roll;
    int marks;

    // Constructor 1: name only
    Student(String name) {
        this.name = name; // "this.name" is the field; "name" is the parameter
        this.roll = 0;
        this.marks = 0;
        System.out.println("Constructor: Student(name)");
    }

    // Constructor 2: name + roll
    Student(String name, int roll) {
        this.name = name;
        this.roll = roll;
        this.marks = 0;
        System.out.println("Constructor: Student(name, roll)");
    }

    // Constructor 3: name + roll + marks
    Student(String name, int roll, int marks) {
        this.name = name;
        this.roll = roll;
        this.marks = marks;
        System.out.println("Constructor: Student(name, roll, marks)");
    }

    void show() {
        System.out.println("Name : " + name + " | Roll : " + roll + " | Marks : " + marks);
    }
}

public class Q07_StudentConstructors {
    public static void main(String[] args) {
        // STEP 1: we only know the name
        Student s1 = new Student("Riya");
        s1.show();
        System.out.println();

        // STEP 2: name and roll number
        Student s2 = new Student("Aman", 21);
        s2.show();
        System.out.println();

        // STEP 3: complete data
        Student s3 = new Student("Neha", 34, 88);
        s3.show();
    }
}
