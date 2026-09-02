package partD.q24;

/*
 * QUESTION 24  (ALL THREE IDEAS IN ONE REAL-LIFE EXAMPLE)
 * ---------
 * We model a COLLEGE LIBRARY BOOK.
 *
 * 1) CONSTRUCTOR OVERLOADING  — ways to register a book
 *      new LibraryBook()
 *      new LibraryBook("Java Basics")
 *      new LibraryBook("Java Basics", "Balagurusamy", 3)
 *
 * 2) METHOD OVERLOADING       — ways to issue / display
 *      issue()                 issue 1 copy to "Guest"
 *      issue(String member)    issue 1 copy to a named student
 *      issue(String member, int copies)
 *
 * 3) OBJECT AS PARAMETER      — merge leftover copies from another book
 *      receiveFrom(LibraryBook other)
 *
 * Follow the numbered STEPS in main().
 */

class LibraryBook {
    String title;
    String author;
    int copies;

    LibraryBook() {
        this("Untitled", "Unknown", 0);
        System.out.println("Used: LibraryBook()");
    }

    LibraryBook(String title) {
        this(title, "Unknown", 1);
        System.out.println("Used: LibraryBook(title)");
    }

    LibraryBook(String title, String author, int copies) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        System.out.println("Used: LibraryBook(title, author, copies)");
    }

    void issue() {
        issue("Guest", 1);
    }

    void issue(String member) {
        issue(member, 1);
    }

    void issue(String member, int wanted) {
        System.out.println("Issue request: " + wanted + " copy/copies of \""
                + title + "\" to " + member);
        if (wanted <= 0) {
            System.out.println("  Nothing to issue.");
            return;
        }
        if (copies < wanted) {
            System.out.println("  Sorry, only " + copies + " left.");
            return;
        }
        copies = copies - wanted;
        System.out.println("  Issued. Remaining copies = " + copies);
    }

    // Move leftover copies FROM other TO this (same title assumed for the demo)
    void receiveFrom(LibraryBook other) {
        System.out.println("Moving " + other.copies + " copies from \""
                + other.title + "\" into \"" + title + "\"");
        this.copies = this.copies + other.copies;
        other.copies = 0;
    }

    void show() {
        System.out.println("Book: \"" + title + "\" by " + author
                + " | copies = " + copies);
    }
}

public class Q24_LibraryBookExample {
    public static void main(String[] args) {
        System.out.println("STEP 1 — constructor overloading (register books)");
        LibraryBook blank = new LibraryBook();
        LibraryBook java = new LibraryBook("Java Basics", "Balagurusamy", 5);
        LibraryBook extraJava = new LibraryBook("Java Basics (old stock)");
        blank.show();
        java.show();
        extraJava.show();
        System.out.println();

        System.out.println("STEP 2 — method overloading (issue copies)");
        java.issue();
        java.issue("Aisha");
        java.issue("Rohan", 2);
        java.show();
        System.out.println();

        System.out.println("STEP 3 — object as parameter (merge stock)");
        java.receiveFrom(extraJava);
        java.show();
        extraJava.show();
    }
}
