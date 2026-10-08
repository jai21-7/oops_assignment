package javaio.q06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/*
 * Append vs overwrite
 * -------------------
 * new FileWriter(file)           → erase old content
 * new FileWriter(file, true)     → append at the end
 */

public class Q06_AppendFile {
    public static void main(String[] args) {
        File file = new File("out/io-demo/log.txt");
        file.getParentFile().mkdirs();
        try {
            FileWriter first = new FileWriter(file); // overwrite
            first.write("first run\n");
            first.close();

            FileWriter extra = new FileWriter(file, true); // append
            extra.write("second run (appended)\n");
            extra.close();

            System.out.print(Files.readString(file.toPath()));
        } catch (IOException e) {
            System.out.println("Append failed: " + e.getMessage());
        }
    }
}
