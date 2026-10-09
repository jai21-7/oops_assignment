package sms.model;

import java.util.ArrayList;
import java.util.List;

/*
 * STEP 2 — Inheritance, plus HAS-A for marks and attendance
 * ---------------------------------------------------------
 * Student extends Person, so a student IS-A person.
 * The child gets name and age, and adds what only a student has:
 * roll number, course, subject marks, and attendance.
 *
 * super(name, age) must run first. It fills the Person part
 * using Person's checks. We do not copy those checks into Student.
 *
 * rollNumber is final. It is the identity we will use as a Map key
 * in Step 4. If roll could change, the Map would still point at the
 * old number and search would break.
 *
 * marks is private. getMarks() returns a copy, so outside code cannot
 * do student.getMarks().clear() and wipe the real list.
 */

public class Student extends Person {

    private final int rollNumber;
    private String course;
    private final List<SubjectMark> marks;
    private final Attendance attendance;

    public Student(int rollNumber, String name, int age, String course) {
        super(name, age);
        if (rollNumber <= 0) {
            throw new IllegalArgumentException("Roll number must be a positive integer.");
        }
        this.rollNumber = rollNumber;
        this.marks = new ArrayList<SubjectMark>();
        this.attendance = new Attendance();
        setCourse(course);
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be empty.");
        }
        String cleaned = course.trim();
        if (cleaned.length() < 2 || cleaned.length() > 40) {
            throw new IllegalArgumentException("Course must be 2 to 40 characters.");
        }
        if (cleaned.indexOf('|') >= 0) {
            throw new IllegalArgumentException("Course cannot contain the | character.");
        }
        this.course = cleaned;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    /** A copy. Changing the returned list does not change the student. */
    public List<SubjectMark> getMarks() {
        return List.copyOf(marks);
    }

    /**
     * Add a subject, or replace marks if that subject is already there.
     * "Java" and "java" count as the same subject.
     */
    public void putMark(String subject, int score) {
        SubjectMark incoming = new SubjectMark(subject, score);
        for (int i = 0; i < marks.size(); i++) {
            SubjectMark existing = marks.get(i);
            if (existing.getSubject().equalsIgnoreCase(incoming.getSubject())) {
                existing.setMarks(score);
                existing.setSubject(incoming.getSubject());
                return;
            }
        }
        marks.add(incoming);
    }

    /** @return true when a matching subject was removed */
    public boolean removeMark(String subject) {
        if (subject == null) {
            return false;
        }
        String cleaned = subject.trim();
        for (int i = 0; i < marks.size(); i++) {
            if (marks.get(i).getSubject().equalsIgnoreCase(cleaned)) {
                marks.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * CGPA = average of each subject's grade point.
     * No subjects yet means 0. A failed subject still counts (grade point 0),
     * so it pulls the average down.
     */
    public double cgpa() {
        if (marks.isEmpty()) {
            return 0.0;
        }
        int totalPoints = 0;
        for (int i = 0; i < marks.size(); i++) {
            totalPoints = totalPoints + marks.get(i).gradePoint();
        }
        return (double) totalPoints / marks.size();
    }

    /** One line for the "list everyone" screen. */
    public String summaryLine() {
        return String.format(
                "Roll %-6d  %-20s  Age %-3d  %-12s  CGPA %4.2f  Attendance %5.1f%%",
                rollNumber,
                getName(),
                getAge(),
                course,
                cgpa(),
                attendance.percentage());
    }

    public String reportCard() {
        StringBuilder card = new StringBuilder();
        card.append("----------------------------------------\n");
        card.append("Roll number : ").append(rollNumber).append('\n');
        card.append("Name        : ").append(getName()).append('\n');
        card.append("Age         : ").append(getAge()).append('\n');
        card.append("Course      : ").append(course).append('\n');
        card.append("Marks\n");
        if (marks.isEmpty()) {
            card.append("  (no subjects yet)\n");
        } else {
            for (int i = 0; i < marks.size(); i++) {
                SubjectMark mark = marks.get(i);
                card.append(String.format(
                        "  %-16s %3d   %-2s   point %d%n",
                        mark.getSubject(),
                        mark.getMarks(),
                        mark.gradeLetter(),
                        mark.gradePoint()));
            }
        }
        card.append(String.format("CGPA        : %.2f%n", cgpa()));
        String status = attendance.isShortage() ? "SHORTAGE" : "OK";
        if (attendance.getClassesHeld() == 0) {
            status = "no classes yet";
        }
        card.append(String.format(
                "Attendance  : %d/%d (%.2f%%)  %s%n",
                attendance.getClassesAttended(),
                attendance.getClassesHeld(),
                attendance.percentage(),
                status));
        card.append("----------------------------------------");
        return card.toString();
    }
}
