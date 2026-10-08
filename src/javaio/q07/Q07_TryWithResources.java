package javaio.q07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * try-with-resources (Java 7+)
 * ----------------------------
 * try (BufferedReader br = new BufferedReader(...)) { ... }
 *
 * Java calls close() AUTOMATICALLY, even if an exception happens.
 * Prefer this over remembering br.close() yourself.
 */

public class Q07_TryWithResources {
    public static void main(String[] args) {
        File file = new File("out/io-demo/auto-close.txt");
        file.getParentFile().mkdirs();
        try (FileWriter w = new FileWriter(file)) {
            w.write("this file is closed automatically\n");
        } catch (IOException e) {
            System.out.println("write: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            System.out.println("read: " + e.getMessage());
        }
    }
}
