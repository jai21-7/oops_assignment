/*
 * QUESTION 4
 * ---------
 * Create a Student class with OVERLOADED display() methods to show:
 *   - only the name
 *   - name with marks
 *   - name with marks and grade
 *
 * BEGINNER IDEA:
 *   Sometimes you have little data, sometimes you have more.
 *   Overloading lets you print whatever information you currently have,
 *   without inventing new method names like displayName(), displayNameMarks(), etc.
 *
 * GRADE (simple rule used here):
 *   marks >= 90 -> A
 *   marks >= 75 -> B
 *   marks >= 50 -> C
 *   otherwise   -> F
 */

class Student {

    void display(String name) {
        System.out.println("Called: display(name)");
        System.out.println("Student name : " + name);
    }

    void display(String name, int marks) {
        System.out.println("Called: display(name, marks)");
        System.out.println("Student name : " + name);
        System.out.println("Marks        : " + marks);
    }

    void display(String name, int marks, char grade) {
        System.out.println("Called: display(name, marks, grade)");
        System.out.println("Student name : " + name);
        System.out.println("Marks        : " + marks);
        System.out.println("Grade        : " + grade);
    }

    // Helper: turn marks into a grade letter so main() stays easy to read
    char gradeFromMarks(int marks) {
        if (marks >= 90) {
            return 'A';
        } else if (marks >= 75) {
            return 'B';
        } else if (marks >= 50) {
            return 'C';
        } else {
            return 'F';
        }
    }
}

public class Q04_StudentDisplay {
    public static void main(String[] args) {
        Student s = new Student();

        // STEP 1: we only know the name
        s.display("Aisha");
        System.out.println();

        // STEP 2: we also know marks
        s.display("Aisha", 82);
        System.out.println();

        // STEP 3: we know name, marks, and grade
        char grade = s.gradeFromMarks(82);
        s.display("Aisha", 82, grade);
    }
}
