package inheritance.partC.q15;

/*
 * QUESTION 15 — Online shopping: Product → ElectronicProduct → Mobile
 * -------------------------------------------------------------------
 * Product:
 *   name, price
 *   instance displayDetails()
 *   static   category()
 *
 * ElectronicProduct: just a middle layer (brand).
 *
 * Mobile:
 *   OVERRIDES displayDetails()
 *   HIDES     static category()
 *
 * Product p = new Mobile(...);
 * Mobile  m = new Mobile(...);
 *
 * ANSWERS the question asks:
 * (1) Overriding : displayDetails()
 * (2) Hiding     : category()
 * (3) Output differs because instance methods use the object,
 *     static methods use the reference type.
 * (4) Compile time : which static category() to call; also whether
 *     the call is legal on that reference.
 * (5) Runtime      : which displayDetails() body actually runs.
 */

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Product: " + name + " | Rs. " + price);
    }

    static void category() {
        System.out.println("Category: General Product");
    }
}

class ElectronicProduct extends Product {
    String brand;

    ElectronicProduct(String name, double price, String brand) {
        super(name, price);
        this.brand = brand;
    }
}

class Mobile extends ElectronicProduct {
    int ramGb;

    Mobile(String name, double price, String brand, int ramGb) {
        super(name, price, brand);
        this.ramGb = ramGb;
    }

    @Override
    void displayDetails() {
        System.out.println("Mobile: " + name + " | " + brand
                + " | " + ramGb + " GB RAM | Rs. " + price);
    }

    static void category() {
        System.out.println("Category: Mobile Phones");
    }
}

public class Q15_ShoppingSystem {
    public static void main(String[] args) {
        Product p = new Mobile("Nova X", 24999, "Acme", 8);
        Mobile m = new Mobile("Nova X", 24999, "Acme", 8);

        System.out.println("--- through Product p = new Mobile() ---");
        p.displayDetails();
        p.category();

        System.out.println("--- through Mobile m = new Mobile() ---");
        m.displayDetails();
        m.category();

        System.out.println();
        System.out.println("(1) Overriding : displayDetails()  p and m both print Mobile details");
        System.out.println("(2) Hiding     : category()        p prints General, m prints Mobile");
        System.out.println("(3) Difference : object vs reference (instance vs static)");
        System.out.println("(4) Compile time: static category() chosen from reference type");
        System.out.println("(5) Runtime     : displayDetails() chosen from actual Mobile object");
    }
}
