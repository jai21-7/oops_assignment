package sms.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sms.model.Student;

/*
 * STEP 4 — Collections
 * --------------------
 * This class is the in-memory office of the system. It does not print menus
 * and it does not open files. It only keeps Student objects and answers
 * add / update / delete / search.
 *
 * Two collections, on purpose:
 *
 *   ArrayList<Student> students
 *     Keeps insertion order. "List everyone" walks this list.
 *     Search by name also walks this list (a linear search).
 *
 *   HashMap<Integer, Student> byRoll
 *     Key = roll number, value = the same Student object that sits in the list.
 *     Finding roll 101 does not scan every student. The map jumps to the key.
 *
 * Both structures must change together. addStudent puts the object in the
 * list AND the map. deleteStudent removes it from both. If you update only
 * one, the program will lie (a student shows up in the list but search by
 * roll says "not found", or the opposite).
 *
 * The map does not copy the student. Both collections point at one object.
 * Change the name, and both "views" see the new name. That is a reference,
 * not a second student.
 */

public class StudentRegistry {

    private final List<Student> students;
    private final Map<Integer, Student> byRoll;

    public StudentRegistry() {
        students = new ArrayList<Student>();
        byRoll = new HashMap<Integer, Student>();
    }

    public int size() {
        return students.size();
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (byRoll.containsKey(student.getRollNumber())) {
            throw new IllegalArgumentException(
                    "Roll number " + student.getRollNumber() + " is already used.");
        }
        students.add(student);
        byRoll.put(student.getRollNumber(), student);
    }

    /**
     * Roll number stays the same. Name, age, and course can change.
     * Person.setName and Person.setAge still enforce the rules from Step 1.
     */
    public Student updateStudent(int rollNumber, String name, int age, String course) {
        Student student = require(rollNumber);
        // Remember the old values. If age is rejected after the name was
        // already changed, put the old values back so the object stays whole.
        String oldName = student.getName();
        int oldAge = student.getAge();
        String oldCourse = student.getCourse();
        try {
            student.setName(name);
            student.setAge(age);
            student.setCourse(course);
        } catch (RuntimeException e) {
            student.setName(oldName);
            student.setAge(oldAge);
            student.setCourse(oldCourse);
            throw e;
        }
        return student;
    }

    public Student deleteStudent(int rollNumber) {
        Student student = require(rollNumber);
        students.remove(student);
        byRoll.remove(rollNumber);
        return student;
    }

    /** @return the student, or null when that roll is not in the registry */
    public Student findByRoll(int rollNumber) {
        return byRoll.get(rollNumber);
    }

    /**
     * Partial, case-insensitive name search.
     * "ra" matches "Asha Rao" and "Ravi". An empty query matches nobody.
     */
    public List<Student> findByName(String partOfName) {
        List<Student> found = new ArrayList<Student>();
        if (partOfName == null) {
            return found;
        }
        String needle = partOfName.trim().toLowerCase();
        if (needle.isEmpty()) {
            return found;
        }
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            String name = student.getName().toLowerCase();
            if (name.contains(needle)) {
                found.add(student);
            }
        }
        return found;
    }

    /** A copy of the list, in insertion order. */
    public List<Student> all() {
        return List.copyOf(students);
    }

    /**
     * Used after loading a file. Throws away whatever is in memory and
     * rebuilds BOTH the list and the map from the loaded students.
     */
    public void replaceAll(List<Student> loaded) {
        students.clear();
        byRoll.clear();
        if (loaded == null) {
            return;
        }
        for (int i = 0; i < loaded.size(); i++) {
            addStudent(loaded.get(i));
        }
    }

    private Student require(int rollNumber) {
        Student student = byRoll.get(rollNumber);
        if (student == null) {
            throw new IllegalArgumentException("No student with roll number " + rollNumber + ".");
        }
        return student;
    }
}
