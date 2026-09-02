package partC.q13;

/*
 * QUESTION 13
 * ---------
 * Student has roll number and marks.
 * compare(Student s) compares marks of TWO Student objects.
 *
 * WHAT DOES "OBJECT AS PARAMETER" MEAN? (beginner)
 *   Until now, methods took int, double, String...
 *   You can also pass a WHOLE OBJECT:
 *
 *     boolean result = s1.compare(s2);
 *                              ^^^^
 *                     s2 is a Student object going into the method
 *
 *   Inside compare, you use:
 *     this.marks   -> marks of the object that CALLED the method (s1)
 *     s.marks      -> marks of the object that was PASSED IN    (s2)
 *
 *   "this" = current object (the one before the dot).
 */

class Student {
    int roll;
    int marks;

    Student(int roll, int marks) {
        this.roll = roll;
        this.marks = marks;
    }

    // s is "the other student"
    void compare(Student s) {
        System.out.println("Comparing roll " + this.roll + " (" + this.marks
                + " marks) with roll " + s.roll + " (" + s.marks + " marks)");

        if (this.marks > s.marks) {
            System.out.println("Student " + this.roll + " has higher marks.");
        } else if (this.marks < s.marks) {
            System.out.println("Student " + s.roll + " has higher marks.");
        } else {
            System.out.println("Both students have the same marks.");
        }
    }
}

public class Q13_StudentCompare {
    public static void main(String[] args) {
        // STEP 1: create two separate objects in memory
        Student s1 = new Student(10, 78);
        Student s2 = new Student(20, 91);

        // STEP 2: pass s2 INTO s1's method
        s1.compare(s2);

        System.out.println();
        Student s3 = new Student(30, 78);
        s1.compare(s3); // equal marks
    }
}
