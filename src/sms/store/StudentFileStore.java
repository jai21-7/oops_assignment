package sms.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sms.model.Student;
import sms.model.SubjectMark;

/*
 * STEP 5 — File handling
 * ----------------------
 * The registry lives in RAM. Close the program and that memory is gone.
 * This class writes three plain text files and reads them back.
 *
 *   students.txt     roll|name|age|course
 *   marks.txt        roll|subject|marks
 *   attendance.txt   roll|classesHeld|classesAttended
 *
 * Why text, not a .ser serialization file?
 *   You can open these in any editor and SEE the data. That makes the
 *   lesson checkable: add a student, save, open students.txt.
 *
 * Why three files?
 *   Each line has one kind of fact. Mixing marks onto the student line
 *   gets messy as soon as one student has a different number of subjects.
 *
 * The | character is the separator. Names cannot contain | (Person
 * already rejects it). Lines that start with # are comments and are skipped.
 *
 * try-with-resources closes the reader/writer even when an exception is thrown.
 * That is the same idea as the Java I/O lecture (Q07).
 */

public class StudentFileStore {

    private final Path directory;

    public StudentFileStore(Path directory) {
        if (directory == null) {
            throw new IllegalArgumentException("Data directory cannot be null.");
        }
        this.directory = directory;
    }

    public Path getDirectory() {
        return directory;
    }

    public boolean hasSavedData() {
        return Files.exists(studentsFile());
    }

    public void save(List<Student> students) throws IOException {
        Files.createDirectories(directory);
        writeStudents(students);
        writeMarks(students);
        writeAttendance(students);
    }

    /**
     * Read the three files into new Student objects.
     * Missing files mean "nothing saved yet" and return an empty list.
     * A broken line stops the load so we do not silently drop a student.
     */
    public List<Student> load() throws IOException {
        if (!Files.exists(studentsFile())) {
            return new ArrayList<Student>();
        }

        List<Student> loaded = readStudents();
        // Temporary map so marks and attendance can find a student by roll
        // without scanning the list for every line.
        Map<Integer, Student> byRoll = new HashMap<Integer, Student>();
        for (int i = 0; i < loaded.size(); i++) {
            Student student = loaded.get(i);
            byRoll.put(student.getRollNumber(), student);
        }

        if (Files.exists(marksFile())) {
            readMarks(byRoll);
        }
        if (Files.exists(attendanceFile())) {
            readAttendance(byRoll);
        }
        return loaded;
    }

    private void writeStudents(List<Student> students) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(studentsFile(), StandardCharsets.UTF_8)) {
            writer.write("# roll|name|age|course");
            writer.newLine();
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                writer.write(student.getRollNumber()
                        + "|" + student.getName()
                        + "|" + student.getAge()
                        + "|" + student.getCourse());
                writer.newLine();
            }
        }
    }

    private void writeMarks(List<Student> students) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(marksFile(), StandardCharsets.UTF_8)) {
            writer.write("# roll|subject|marks");
            writer.newLine();
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                List<SubjectMark> marks = student.getMarks();
                for (int m = 0; m < marks.size(); m++) {
                    SubjectMark mark = marks.get(m);
                    writer.write(student.getRollNumber()
                            + "|" + mark.getSubject()
                            + "|" + mark.getMarks());
                    writer.newLine();
                }
            }
        }
    }

    private void writeAttendance(List<Student> students) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(attendanceFile(), StandardCharsets.UTF_8)) {
            writer.write("# roll|classesHeld|classesAttended");
            writer.newLine();
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                writer.write(student.getRollNumber()
                        + "|" + student.getAttendance().getClassesHeld()
                        + "|" + student.getAttendance().getClassesAttended());
                writer.newLine();
            }
        }
    }

    private List<Student> readStudents() throws IOException {
        List<Student> loaded = new ArrayList<Student>();
        try (BufferedReader reader = Files.newBufferedReader(studentsFile(), StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber = lineNumber + 1;
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split("\\|", -1);
                if (parts.length != 4) {
                    throw new IOException(studentsFile() + " line " + lineNumber + " must have 4 fields.");
                }
                try {
                    int roll = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    String course = parts[3].trim();
                    loaded.add(new Student(roll, name, age, course));
                } catch (NumberFormatException e) {
                    throw new IOException(studentsFile() + " line " + lineNumber + " has a bad number.", e);
                } catch (IllegalArgumentException e) {
                    throw new IOException(studentsFile() + " line " + lineNumber + ": " + e.getMessage(), e);
                }
            }
        }
        return loaded;
    }

    private void readMarks(Map<Integer, Student> byRoll) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(marksFile(), StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber = lineNumber + 1;
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split("\\|", -1);
                if (parts.length != 3) {
                    throw new IOException(marksFile() + " line " + lineNumber + " must have 3 fields.");
                }
                try {
                    int roll = Integer.parseInt(parts[0].trim());
                    Student student = byRoll.get(roll);
                    if (student == null) {
                        throw new IOException("marks.txt line " + lineNumber + " refers to unknown roll " + roll + ".");
                    }
                    int score = Integer.parseInt(parts[2].trim());
                    student.putMark(parts[1], score);
                } catch (NumberFormatException e) {
                    throw new IOException(marksFile() + " line " + lineNumber + " has a bad number.", e);
                }
            }
        }
    }

    private void readAttendance(Map<Integer, Student> byRoll) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(attendanceFile(), StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber = lineNumber + 1;
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split("\\|", -1);
                if (parts.length != 3) {
                    throw new IOException(attendanceFile() + " line " + lineNumber + " must have 3 fields.");
                }
                try {
                    int roll = Integer.parseInt(parts[0].trim());
                    Student student = byRoll.get(roll);
                    if (student == null) {
                        throw new IOException("attendance.txt line " + lineNumber
                                + " refers to unknown roll " + roll + ".");
                    }
                    int held = Integer.parseInt(parts[1].trim());
                    int attended = Integer.parseInt(parts[2].trim());
                    student.getAttendance().setTotals(held, attended);
                } catch (NumberFormatException e) {
                    throw new IOException(attendanceFile() + " line " + lineNumber + " has a bad number.", e);
                }
            }
        }
    }

    private Path studentsFile() {
        return directory.resolve("students.txt");
    }

    private Path marksFile() {
        return directory.resolve("marks.txt");
    }

    private Path attendanceFile() {
        return directory.resolve("attendance.txt");
    }
}
