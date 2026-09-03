package inheritance.partC.q13;

/*
 * QUESTION 13 — Static hiding through THREE levels
 * ------------------------------------------------
 * GrandParent → Parent → Child
 * Each has static void show().
 *
 * PREDICTION (write this down before you run):
 *   GrandParent g = new Child();  g.show() → GrandParent
 *   Parent      p = new Child();  p.show() → Parent
 *   Child       c = new Child();  c.show() → Child
 *
 * Reason: every show() is static, so only the LEFT side (reference) matters.
 * All three objects are Child, but that does not change static calls.
 */

class GrandParent {
    static void show() {
        System.out.println("GrandParent static show()");
    }
}

class Parent extends GrandParent {
    static void show() {
        System.out.println("Parent static show()");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Child static show()");
    }
}

public class Q13_ThreeLevelHiding {
    public static void main(String[] args) {
        System.out.println("PREDICTION:");
        System.out.println("  g.show() -> GrandParent");
        System.out.println("  p.show() -> Parent");
        System.out.println("  c.show() -> Child");
        System.out.println();
        System.out.println("ACTUAL:");

        GrandParent g = new Child();
        Parent p = new Child();
        Child c = new Child();

        g.show();
        p.show();
        c.show();
    }
}
