package inheritance.partC.q11;

/*
 * QUESTION 11 — Method HIDING (static methods)
 * --------------------------------------------
 * Parent and Child both have:  static void show()
 *
 * THIS IS NOT OVERRIDING.
 * Static methods are HIDDEN, not overridden.
 *
 * WHY?
 *   Static methods belong to the CLASS, not to the object.
 *   The compiler looks at the REFERENCE TYPE (left side) and picks
 *   Parent.show() or Child.show() before the program even runs.
 *
 * TEST:
 *   Parent p = new Child();   // reference Parent, object Child
 *   Child  c = new Child();
 *
 *   p.show();  → Parent.show()   because p is declared as Parent
 *   c.show();  → Child.show()    because c is declared as Child
 *
 * PREDICTION before running: Parent version, then Child version.
 * If this were overriding, BOTH would print Child. They do not.
 */

class Parent {
    static void show() {
        System.out.println("Parent static show()");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Child static show()");
    }
}

public class Q11_StaticHiding {
    public static void main(String[] args) {
        Parent p = new Child();
        Child c = new Child();

        System.out.println("Call p.show()  with Parent p = new Child()");
        p.show();

        System.out.println("Call c.show()  with Child c = new Child()");
        c.show();

        System.out.println();
        System.out.println("WHY: show() is static. Java uses the reference type.");
        System.out.println("p is Parent → Parent.show()");
        System.out.println("c is Child  → Child.show()");
        System.out.println("The object on the right (new Child) is ignored for static methods.");
    }
}
