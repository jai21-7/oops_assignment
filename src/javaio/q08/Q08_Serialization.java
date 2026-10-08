package javaio.q08;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * Serialization — save an OBJECT to a file, then load it back
 * -----------------------------------------------------------
 * The class must implement Serializable (a marker interface: no methods).
 *
 * ObjectOutputStream.writeObject(student)
 * ObjectInputStream.readObject()
 *
 * Use case: save a Student to disk between program runs.
 */

class Student implements Serializable {
    String name;
    int roll;

    Student(String name, int roll) {
        this.name = name;
        this.roll = roll;
    }

    public String toString() {
        return name + " roll=" + roll;
    }
}

public class Q08_Serialization {
    public static void main(String[] args) {
        File file = new File("out/io-demo/student.ser");
        file.getParentFile().mkdirs();
        try {
            Student original = new Student("Neha", 21);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(original);
            out.close();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Student loaded = (Student) in.readObject();
            in.close();

            System.out.println("Saved then loaded: " + loaded);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Serialization failed: " + e.getMessage());
        }
    }
}
