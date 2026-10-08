package interfaces.q02;

/*
 * Multiple inheritance through interfaces
 * ---------------------------------------
 * A class can extend only ONE class, but implement MANY interfaces.
 *
 *   class Demo implements Printable, Showable
 *
 * Both interfaces have methods; Demo writes both.
 */

interface Printable {
    void print();
}

interface Showable {
    void show();
}

class Document implements Printable, Showable {
    public void print() {
        System.out.println("Printing the document...");
    }

    public void show() {
        System.out.println("Showing preview on screen...");
    }
}

public class Q02_MultipleInterfaces {
    public static void main(String[] args) {
        Document d = new Document();
        d.print();
        d.show();
    }
}
