package javaio.q02;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Character INPUT — FileReader
 * ----------------------------
 * FileReader.read() returns one character as an int, or -1 at end of file.
 *
 * We first make sure the file exists (write if needed), then read it char by char.
 */

public class Q02_FileReaderDemo {
    public static void main(String[] args) {
        File file = new File("out/io-demo/notes.txt");
        file.getParentFile().mkdirs();
        try {
            if (!file.exists()) {
                java.io.FileWriter w = new java.io.FileWriter(file);
                w.write("Hello\n");
                w.close();
            }
            FileReader r = new FileReader(file);
            int ch;
            System.out.print("File contents: ");
            while ((ch = r.read()) != -1) {
                System.out.print((char) ch);
            }
            r.close();
        } catch (IOException e) {
            System.out.println("Read failed: " + e.getMessage());
        }
    }
}
