package partD.q22;

/*
 * QUESTION 22  (COMBINED)
 * ---------
 * Product with overloaded constructors (different ways to create a product)
 * plus comparePrice(Product p).
 *
 * Real life: a shop item might be registered with only a name,
 * or with name+price, or with full details (name, price, stock).
 */

class Product {
    String name;
    double price;
    int stock;

    Product(String name) {
        this(name, 0, 0);
    }

    Product(String name, double price) {
        this(name, price, 0);
    }

    Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    void comparePrice(Product p) {
        System.out.println(name + " costs " + price
                + " | " + p.name + " costs " + p.price);
        if (price > p.price) {
            System.out.println(name + " is more expensive.");
        } else if (price < p.price) {
            System.out.println(p.name + " is more expensive.");
        } else {
            System.out.println("Both products have the same price.");
        }
    }

    void show() {
        System.out.println(name + " | Rs. " + price + " | stock " + stock);
    }
}

public class Q22_ProductComparePrice {
    public static void main(String[] args) {
        Product p1 = new Product("Notebook");
        Product p2 = new Product("Pen", 20);
        Product p3 = new Product("Bag", 799, 12);

        p1.show();
        p2.show();
        p3.show();
        System.out.println();

        p2.comparePrice(p3);
    }
}
