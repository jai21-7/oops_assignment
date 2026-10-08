package interfaces.q03;

/*
 * Interface can EXTEND another interface
 * --------------------------------------
 * Same idea as class inheritance, but with interfaces.
 *
 *   interface Sports { void play(); }
 *   interface Exam { void marks(); }
 *   interface Result extends Sports, Exam { void display(); }
 *
 * A class that implements Result must write play, marks, AND display.
 */

interface Sports {
    void play();
}

interface Exam {
    void marks();
}

interface Result extends Sports, Exam {
    void display();
}

class Student implements Result {
    public void play() {
        System.out.println("Plays cricket for the college.");
    }

    public void marks() {
        System.out.println("Scored 82 in the exam.");
    }

    public void display() {
        System.out.println("Result card ready.");
    }
}

public class Q03_InterfaceExtends {
    public static void main(String[] args) {
        Student s = new Student();
        s.play();
        s.marks();
        s.display();
    }
}
