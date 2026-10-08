package javaio.q03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * BufferedReader / BufferedWriter
 * -------------------------------
 * FileReader reads ONE char at a time (slow for big files).
 * BufferedReader wraps it and can read a WHOLE LINE: readLine().
 *
 * readLine() returns null at end of file (not "").
 *
 * Pattern:
 *   BufferedReader in = new BufferedReader(new FileReader(file));
 */

public class Q03_BufferedLineIO {
    public static void main(String[] args) {
        File dir = new File("out/io-demo");
        dir.mkdirs();
        File file = new File(dir, "lines.txt");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write("apple");
            out.newLine();
            out.write("banana");
            out.newLine();
            out.write("cherry");
            out.newLine();
            out.close();

            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            int n = 0;
            while ((line = in.readLine()) != null) {
                n++;
                System.out.println("Line " + n + ": " + line);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("I/O failed: " + e.getMessage());
        }
    }
}
