package inheritance.partC.q12;

/*
 * QUESTION 12 — Override vs hide in the SAME pair of classes
 * ----------------------------------------------------------
 * Parent:
 *   instance display()
 *   static   show()
 * Child:
 *   overrides display()     ← instance = OVERRIDING
 *   defines   static show() ← static   = HIDING
 *
 * Parent p = new Child();
 * Child  c = new Child();
 *
 * display()  depends on the OBJECT     (runtime)
 * show()     depends on the REFERENCE  (compile time)
 *
 * EXPECTED:
 *   p.display() → Child display     (object is Child)
 *   c.display() → Child display     (object is Child)
 *   p.show()    → Parent show       (reference is Parent)
 *   c.show()    → Child show        (reference is Child)
 */

class Parent {
    void display() {
        System.out.println("Parent instance display()");
    }

    static void show() {
        System.out.println("Parent static show()");
    }
}

class Child extends Parent {
    @Override
    void display() {
        System.out.println("Child instance display()");
    }

    static void show() {
        System.out.println("Child static show()");
    }
}

public class Q12_OverrideVsHide {
    public static void main(String[] args) {
        Parent p = new Child();
        Child c = new Child();

        System.out.println("--- display() instance: chosen by OBJECT ---");
        p.display();
        c.display();

        System.out.println("--- show() static: chosen by REFERENCE ---");
        p.show();
        c.show();

        System.out.println();
        System.out.println("Overridden : display()  (depends on object type)");
        System.out.println("Hidden     : show()     (depends on reference type)");
    }
}
