package sms;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import sms.model.Student;
import sms.store.StudentFileStore;
import sms.store.StudentRegistry;

/*
 * STEP 6 — The menu that uses the classes above
 * ---------------------------------------------
 * Read the model classes first (Person, SubjectMark, Attendance, Student),
 * then StudentRegistry, then StudentFileStore, and only then this file.
 * The lesson that walks through them is STUDENT_SYSTEM.md in the project root.
 *
 * This class talks to the user. It does not invent grade rules or file formats.
 * It asks for input, calls the registry, and saves.
 *
 * Run the menu:
 *   javac -d out src/sms/model/*.java src/sms/store/*.java src/sms/StudentManagementApp.java
 *   java -cp out sms.StudentManagementApp
 *
 * Run the scripted lesson check (no typing):
 *   java -cp out sms.StudentManagementApp demo
 *
 * Data is stored in data/sms/ unless you pass another folder:
 *   java -cp out sms.StudentManagementApp /tmp/my-students
 */

public class StudentManagementApp {

    private final StudentRegistry registry;
    private final StudentFileStore fileStore;
    private final Scanner scanner;

    public StudentManagementApp(StudentRegistry registry, StudentFileStore fileStore, Scanner scanner) {
        this.registry = registry;
        this.fileStore = fileStore;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        Path dataDirectory = Path.of("data", "sms");
        boolean demo = false;
        if (args.length >= 1 && args[0].equals("demo")) {
            demo = true;
            dataDirectory = Path.of("out", "sms-demo");
            if (args.length >= 2) {
                dataDirectory = Path.of(args[1]);
            }
        } else if (args.length >= 1) {
            dataDirectory = Path.of(args[0]);
        }

        StudentRegistry registry = new StudentRegistry();
        StudentFileStore fileStore = new StudentFileStore(dataDirectory);
        if (demo) {
            runDemo(registry, fileStore);
            return;
        }

        try {
            if (fileStore.hasSavedData()) {
                registry.replaceAll(fileStore.load());
                System.out.println("Loaded " + registry.size() + " student(s) from " + fileStore.getDirectory());
            } else {
                System.out.println("No save file yet. A new list will be created in " + fileStore.getDirectory());
            }
        } catch (IOException e) {
            System.out.println("Could not load saved students: " + e.getMessage());
            System.out.println("Starting with an empty list. Fix the files before you rely on Save.");
        }

        Scanner scanner = new Scanner(System.in);
        StudentManagementApp app = new StudentManagementApp(registry, fileStore, scanner);
        app.menuLoop();
    }

    private void menuLoop() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = readLine("Choose: ");
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    updateStudent();
                    break;
                case "3":
                    deleteStudent();
                    break;
                case "4":
                    searchByRoll();
                    break;
                case "5":
                    searchByName();
                    break;
                case "6":
                    listAll();
                    break;
                case "7":
                    addOrUpdateMarks();
                    break;
                case "8":
                    removeSubject();
                    break;
                case "9":
                    showReport();
                    break;
                case "10":
                    recordClass();
                    break;
                case "11":
                    setAttendance();
                    break;
                case "12":
                    showReport();
                    break;
                case "13":
                    saveNow();
                    break;
                case "14":
                    loadNow();
                    break;
                case "0":
                    saveQuietly();
                    System.out.println("Saved and closed. Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Unknown choice. Type a number from the menu.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== Student Management System =====");
        System.out.println(" 1  Add student");
        System.out.println(" 2  Update student (name, age, course)");
        System.out.println(" 3  Delete student");
        System.out.println(" 4  Search by roll number");
        System.out.println(" 5  Search by name");
        System.out.println(" 6  List all students");
        System.out.println(" 7  Add or update marks");
        System.out.println(" 8  Remove a subject");
        System.out.println(" 9  Marksheet and CGPA");
        System.out.println("10  Record one class (present / absent)");
        System.out.println("11  Set attendance totals");
        System.out.println("12  Show attendance");
        System.out.println("13  Save to files");
        System.out.println("14  Load from files (replaces the list in memory)");
        System.out.println(" 0  Save and exit");
    }

    private void addStudent() {
        try {
            int roll = readInt("Roll number: ");
            String name = readLine("Name: ");
            int age = readInt("Age: ");
            String course = readLine("Course: ");
            registry.addStudent(new Student(roll, name, age, course));
            saveQuietly();
            System.out.println("Added roll " + roll + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Not added: " + e.getMessage());
        }
    }

    private void updateStudent() {
        try {
            int roll = readInt("Roll number to update: ");
            Student existing = registry.findByRoll(roll);
            if (existing == null) {
                System.out.println("No student with roll number " + roll + ".");
                return;
            }
            System.out.println("Leave a field blank to keep the current value.");
            System.out.println("Current: " + existing.getName() + ", age " + existing.getAge()
                    + ", " + existing.getCourse());
            String name = readLine("New name: ");
            String ageText = readLine("New age: ");
            String course = readLine("New course: ");
            if (name.isEmpty()) {
                name = existing.getName();
            }
            int age = existing.getAge();
            if (!ageText.isEmpty()) {
                age = Integer.parseInt(ageText);
            }
            if (course.isEmpty()) {
                course = existing.getCourse();
            }
            registry.updateStudent(roll, name, age, course);
            saveQuietly();
            System.out.println("Updated roll " + roll + ".");
        } catch (NumberFormatException e) {
            System.out.println("Not updated: age must be a whole number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Not updated: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        try {
            int roll = readInt("Roll number to delete: ");
            Student removed = registry.deleteStudent(roll);
            saveQuietly();
            System.out.println("Deleted " + removed.getName() + " (roll " + roll + ").");
        } catch (IllegalArgumentException e) {
            System.out.println("Not deleted: " + e.getMessage());
        }
    }

    private void searchByRoll() {
        try {
            int roll = readInt("Roll number: ");
            Student student = registry.findByRoll(roll);
            if (student == null) {
                System.out.println("No student with roll number " + roll + ".");
            } else {
                System.out.println(student.reportCard());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchByName() {
        String part = readLine("Name contains: ");
        List<Student> found = registry.findByName(part);
        if (found.isEmpty()) {
            System.out.println("No student name contains \"" + part + "\".");
            return;
        }
        System.out.println(found.size() + " match(es):");
        for (int i = 0; i < found.size(); i++) {
            System.out.println(found.get(i).summaryLine());
        }
    }

    private void listAll() {
        List<Student> everyone = registry.all();
        if (everyone.isEmpty()) {
            System.out.println("No students yet. Use option 1 to add one.");
            return;
        }
        System.out.println(everyone.size() + " student(s):");
        for (int i = 0; i < everyone.size(); i++) {
            System.out.println(everyone.get(i).summaryLine());
        }
    }

    private void addOrUpdateMarks() {
        try {
            Student student = requireByRoll("Roll number: ");
            if (student == null) {
                return;
            }
            String subject = readLine("Subject: ");
            int marks = readInt("Marks out of 100: ");
            student.putMark(subject, marks);
            saveQuietly();
            System.out.println("Saved marks. CGPA is now " + String.format("%.2f", student.cgpa()) + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Marks not saved: " + e.getMessage());
        }
    }

    private void removeSubject() {
        try {
            Student student = requireByRoll("Roll number: ");
            if (student == null) {
                return;
            }
            String subject = readLine("Subject to remove: ");
            if (student.removeMark(subject)) {
                saveQuietly();
                System.out.println("Removed " + subject + ". CGPA is now "
                        + String.format("%.2f", student.cgpa()) + ".");
            } else {
                System.out.println("That student has no subject named \"" + subject + "\".");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showReport() {
        try {
            Student student = requireByRoll("Roll number: ");
            if (student != null) {
                System.out.println(student.reportCard());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void recordClass() {
        try {
            Student student = requireByRoll("Roll number: ");
            if (student == null) {
                return;
            }
            String answer = readLine("Present? (y/n): ");
            boolean present;
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                present = true;
            } else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
                present = false;
            } else {
                System.out.println("Type y or n.");
                return;
            }
            student.getAttendance().recordClass(present);
            saveQuietly();
            System.out.println(student.reportCard());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setAttendance() {
        try {
            Student student = requireByRoll("Roll number: ");
            if (student == null) {
                return;
            }
            int held = readInt("Classes held: ");
            int attended = readInt("Classes attended: ");
            student.getAttendance().setTotals(held, attended);
            saveQuietly();
            System.out.println(student.reportCard());
        } catch (IllegalArgumentException e) {
            System.out.println("Attendance not saved: " + e.getMessage());
        }
    }

    private void saveNow() {
        try {
            fileStore.save(registry.all());
            System.out.println("Saved " + registry.size() + " student(s) to " + fileStore.getDirectory());
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    private void loadNow() {
        try {
            registry.replaceAll(fileStore.load());
            System.out.println("Loaded " + registry.size() + " student(s) from " + fileStore.getDirectory());
        } catch (IOException e) {
            System.out.println("Load failed: " + e.getMessage());
        }
    }

    /** Changes are written immediately so a crash does not drop the last edit. */
    private void saveQuietly() {
        try {
            fileStore.save(registry.all());
        } catch (IOException e) {
            System.out.println("Warning: could not save files: " + e.getMessage());
        }
    }

    private Student requireByRoll(String prompt) {
        int roll = readInt(prompt);
        Student student = registry.findByRoll(roll);
        if (student == null) {
            System.out.println("No student with roll number " + roll + ".");
        }
        return student;
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine();
        if (line == null) {
            return "";
        }
        return line.trim();
    }

    private int readInt(String prompt) {
        String text = readLine(prompt);
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a whole number.");
        }
    }

    /**
     * A fixed story the program runs by itself.
     * Use it to see the rules work before you type into the menu.
     * If a check fails, the program stops with a message.
     */
    static void runDemo(StudentRegistry registry, StudentFileStore fileStore) {
        try {
            Student asha = new Student(101, "Asha Rao", 20, "BCA");
            Student ravi = new Student(102, "Ravi Menon", 21, "BCA");
            registry.addStudent(asha);
            registry.addStudent(ravi);

            expectFail(new Runnable() {
                public void run() {
                    registry.addStudent(new Student(101, "Other Person", 19, "BCA"));
                }
            }, "duplicate roll");

            expectFail(new Runnable() {
                public void run() {
                    new Student(103, "No", 10, "BCA");
                }
            }, "age too small");

            registry.updateStudent(101, "Asha R. Rao", 20, "BCA");
            check(registry.findByRoll(101).getName().equals("Asha R. Rao"), "update name");

            asha.putMark("Java", 88);
            asha.putMark("Maths", 76);
            asha.putMark("java", 91); // same subject, new score replaces 88
            check(asha.getMarks().size() == 2, "Java is stored once");
            check(asha.getMarks().get(0).getMarks() == 91, "latest Java marks win");
            // Java 91 -> 10, Maths 76 -> 8, average 9.00
            check(Math.abs(asha.cgpa() - 9.0) < 0.001, "CGPA is 9.00");

            expectFail(new Runnable() {
                public void run() {
                    asha.putMark("English", 140);
                }
            }, "marks above 100");

            asha.getAttendance().recordClass(true);
            asha.getAttendance().recordClass(true);
            asha.getAttendance().recordClass(false);
            check(asha.getAttendance().getClassesHeld() == 3, "three classes held");
            check(asha.getAttendance().getClassesAttended() == 2, "two presents");
            check(asha.getAttendance().isShortage(), "2/3 is below 75%");

            asha.getAttendance().setTotals(40, 36);
            check(!asha.getAttendance().isShortage(), "36/40 is not a shortage");
            expectFail(new Runnable() {
                public void run() {
                    asha.getAttendance().setTotals(10, 12);
                }
            }, "attended more than held");

            List<Student> named = registry.findByName("rao");
            check(named.size() == 1, "search name rao");
            check(registry.findByRoll(999) == null, "missing roll");

            registry.deleteStudent(102);
            check(registry.findByRoll(102) == null, "deleted ravi");
            check(registry.size() == 1, "one student left");

            fileStore.save(registry.all());
            StudentRegistry again = new StudentRegistry();
            again.replaceAll(fileStore.load());
            Student loaded = again.findByRoll(101);
            check(loaded != null, "loaded roll 101");
            check(loaded.getName().equals("Asha R. Rao"), "loaded name");
            check(loaded.getMarks().size() == 2, "loaded two subjects");
            check(Math.abs(loaded.cgpa() - 9.0) < 0.001, "loaded CGPA");
            check(loaded.getAttendance().getClassesHeld() == 40, "loaded classes held");
            check(loaded.getAttendance().getClassesAttended() == 36, "loaded classes attended");

            System.out.println(loaded.reportCard());
            System.out.println();
            System.out.println("Demo passed. Files are in " + fileStore.getDirectory());
        } catch (IOException e) {
            System.out.println("Demo failed while using files: " + e.getMessage());
            System.exit(1);
        } catch (RuntimeException e) {
            System.out.println("Demo failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void expectFail(Runnable action, String label) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            return;
        }
        throw new IllegalStateException("Expected a rejection for: " + label);
    }

    private static void check(boolean condition, String label) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + label);
        }
    }
}
