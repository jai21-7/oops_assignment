package partB.q12;

/*
 * QUESTION 12
 * ---------
 * Demonstrate CONSTRUCTOR CHAINING using this() with THREE
 * overloaded constructors in a Student class.
 *
 * WHAT IS this()? (beginner)
 *   this() means: "call ANOTHER constructor of the SAME class".
 *   It must be the FIRST statement inside the constructor.
 *
 * WHY CHAIN?
 *   Write the real assignment of fields ONCE (in the fullest constructor).
 *   The smaller constructors just pass default values into that one.
 *   Less copy-paste, fewer mistakes.
 *
 * CHAIN WE WILL BUILD:
 *   Student()
 *     -> this("Unknown")                  // jumps to name-only
 *          -> this("Unknown", 0)          // jumps to name+roll
 *               -> this("Unknown", 0, 0)  // jumps to full constructor
 *
 *   So EVERY object, no matter how you create it, ends in ONE place
 *   that actually stores the values.
 */

class Student {
    String name;
    int roll;
    int marks;

    // Smallest constructor: no data. Hands off to the next one.
    Student() {
        this("Unknown");
        System.out.println("Finished: Student()");
    }

    // Name only. Hands off to name+roll, using roll = 0.
    Student(String name) {
        this(name, 0);
        System.out.println("Finished: Student(name)");
    }

    // Name + roll. Hands off to the FULL constructor, using marks = 0.
    Student(String name, int roll) {
        this(name, roll, 0);
        System.out.println("Finished: Student(name, roll)");
    }

    // FULL constructor: the only place that actually assigns fields.
    Student(String name, int roll, int marks) {
        this.name = name;
        this.roll = roll;
        this.marks = marks;
        System.out.println("Finished: Student(name, roll, marks)  <- real assignments happen here");
    }

    void show() {
        System.out.println("Name : " + name + " | Roll : " + roll + " | Marks : " + marks);
    }
}

public class Q12_StudentChaining {
    public static void main(String[] args) {
        System.out.println("--- Creating with new Student() ---");
        Student s1 = new Student();
        s1.show();
        System.out.println();

        System.out.println("--- Creating with new Student(\"Vikram\") ---");
        Student s2 = new Student("Vikram");
        s2.show();
        System.out.println();

        System.out.println("--- Creating with new Student(\"Isha\", 11, 91) ---");
        Student s3 = new Student("Isha", 11, 91);
        s3.show();
    }
}
