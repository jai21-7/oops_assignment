package inheritance.partA.q04;

/*
 * QUESTION 4 — Multilevel inheritance
 * -----------------------------------
 * Person  →  Student  →  Result
 *
 * Person  : name, age
 * Student : roll number, course   (and everything Person has)
 * Result  : three subject marks   (and everything Student has)
 *
 * MULTILEVEL:
 *   Result extends Student, Student extends Person.
 *   So a Result object HAS name, age, roll, course, AND three marks.
 *
 *   Person
 *     └── Student
 *           └── Result
 *
 * STEPS
 *   1. Each constructor calls super(...) to fill the parent fields.
 *   2. Result computes total and percentage.
 *   3. Create ONE Result object — that is enough to print the whole chain.
 */

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void displayPerson() {
        System.out.println("Name : " + name);
        System.out.println("Age  : " + age);
    }
}

class Student extends Person {
    int roll;
    String course;

    Student(String name, int age, int roll, String course) {
        super(name, age);
        this.roll = roll;
        this.course = course;
    }

    void displayStudent() {
        displayPerson();
        System.out.println("Roll : " + roll);
        System.out.println("Course: " + course);
    }
}

class Result extends Student {
    int m1, m2, m3;

    Result(String name, int age, int roll, String course, int m1, int m2, int m3) {
        super(name, age, roll, course);
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    int total() {
        return m1 + m2 + m3;
    }

    double percentage() {
        return total() / 3.0;
    }

    void displayResult() {
        displayStudent();
        System.out.println("Marks: " + m1 + ", " + m2 + ", " + m3);
        System.out.println("Total: " + total());
        System.out.println("Percentage: " + percentage());
    }
}

public class Q04_PersonStudentResult {
    public static void main(String[] args) {
        Result r = new Result("Isha", 19, 42, "BCA", 80, 75, 90);
        r.displayResult();
    }
}
