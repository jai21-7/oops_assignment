package partB.q11;

/*
 * QUESTION 11
 * ---------
 * Demonstrate constructor overloading in a Book class:
 *   - no arguments
 *   - title only
 *   - title, author, and price
 *
 * BEGINNER IDEA:
 *   Think of filling a library form:
 *     - blank form          -> default constructor
 *     - only book title     -> one-argument constructor
 *     - full catalogue card -> three-argument constructor
 */

class Book {
    String title;
    String author;
    double price;

    Book() {
        title = "Untitled";
        author = "Unknown";
        price = 0;
        System.out.println("Constructor: Book()");
    }

    Book(String title) {
        this.title = title;
        this.author = "Unknown";
        this.price = 0;
        System.out.println("Constructor: Book(title)");
    }

    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        System.out.println("Constructor: Book(title, author, price)");
    }

    void show() {
        System.out.println("\"" + title + "\" by " + author + " | Rs. " + price);
    }
}

public class Q11_BookConstructors {
    public static void main(String[] args) {
        Book b1 = new Book();
        b1.show();
        System.out.println();

        Book b2 = new Book("Wings of Fire");
        b2.show();
        System.out.println();

        Book b3 = new Book("Clean Code", "Robert Martin", 550.0);
        b3.show();
    }
}
