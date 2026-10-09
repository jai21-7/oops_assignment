package sms.model;

/*
 * STEP 3a — A small class for one subject
 * ---------------------------------------
 * One student has MANY subject marks. Each mark is its own object:
 * subject name + marks out of 100.
 *
 * This is composition (HAS-A), not inheritance.
 * A SubjectMark is not a kind of Student. A Student has subject marks.
 *
 * Grade scale used by this project (10-point):
 *   90-100  O   10
 *   80-89   A+   9
 *   70-79   A    8
 *   60-69   B+   7
 *   50-59   B    6
 *   40-49   C    5
 *   0-39    F    0
 */

public class SubjectMark {

    private String subject;
    private int marks;

    public SubjectMark(String subject, int marks) {
        setSubject(subject);
        setMarks(marks);
    }

    public String getSubject() {
        return subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setSubject(String subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject cannot be empty.");
        }
        String cleaned = subject.trim();
        if (cleaned.length() < 2 || cleaned.length() > 30) {
            throw new IllegalArgumentException("Subject must be 2 to 30 characters.");
        }
        if (cleaned.indexOf('|') >= 0) {
            throw new IllegalArgumentException("Subject cannot contain the | character.");
        }
        this.subject = cleaned;
    }

    public void setMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        this.marks = marks;
    }

    /** 10, 9, 8, 7, 6, 5, or 0. Used when we average a student's CGPA. */
    public int gradePoint() {
        if (marks >= 90) {
            return 10;
        }
        if (marks >= 80) {
            return 9;
        }
        if (marks >= 70) {
            return 8;
        }
        if (marks >= 60) {
            return 7;
        }
        if (marks >= 50) {
            return 6;
        }
        if (marks >= 40) {
            return 5;
        }
        return 0;
    }

    public String gradeLetter() {
        int point = gradePoint();
        if (point == 10) {
            return "O";
        }
        if (point == 9) {
            return "A+";
        }
        if (point == 8) {
            return "A";
        }
        if (point == 7) {
            return "B+";
        }
        if (point == 6) {
            return "B";
        }
        if (point == 5) {
            return "C";
        }
        return "F";
    }
}
