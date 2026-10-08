package javaio.q05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Lab: count lines, words, and characters in a text file.
 */

public class Q05_CountFile {
    public static void main(String[] args) {
        File file = new File("out/io-demo/count-me.txt");
        file.getParentFile().mkdirs();
        try {
            FileWriter w = new FileWriter(file);
            w.write("Java I/O is useful.\n");
            w.write("Count these words.\n");
            w.close();

            int lines = 0, words = 0, chars = 0;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                lines++;
                chars += line.length();
                if (!line.trim().isEmpty()) {
                    words += line.trim().split("\\s+").length;
                }
            }
            br.close();
            System.out.println("Lines = " + lines);
            System.out.println("Words = " + words);
            System.out.println("Chars (without newline) = " + chars);
        } catch (IOException e) {
            System.out.println("Count failed: " + e.getMessage());
        }
    }
}
