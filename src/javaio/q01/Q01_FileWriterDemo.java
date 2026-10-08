package javaio.q01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Java I/O lectures 32–33 — character OUTPUT
 * ------------------------------------------
 * FileWriter writes TEXT (characters) to a file.
 *
 * STEPS
 *   1. Create folder out/io-demo
 *   2. new FileWriter(file)  — creates/overwrites the file
 *   3. write(...) some lines
 *   4. close()  — IMPORTANT or data may stay in a buffer and never hit disk
 *
 * IOException is checked: you must catch it or declare throws.
 */

public class Q01_FileWriterDemo {
    public static void main(String[] args) {
        File dir = new File("out/io-demo");
        dir.mkdirs();
        File file = new File(dir, "notes.txt");
        try {
            FileWriter w = new FileWriter(file);
            w.write("Hello Java I/O\n");
            w.write("Second line\n");
            w.close();
            System.out.println("Wrote text to " + file.getPath());
        } catch (IOException e) {
            System.out.println("Write failed: " + e.getMessage());
        }
    }
}
