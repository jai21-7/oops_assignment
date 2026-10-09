package sms.model;

/*
 * STEP 1 — Encapsulation
 * ----------------------
 * A Person is the shared idea behind anyone in the system: a name and an age.
 * Student will EXTEND this class in Step 2. We do not put roll number or
 * marks here, because those belong only to a student.
 *
 * ENCAPSULATION means:
 *   1. Fields are private. Other classes cannot write person.age = -5.
 *   2. Changes go through methods. Those methods check the new value.
 *   3. The class keeps its own data valid.
 *
 * "private" is the lock. The constructor and the setters are the only doors.
 */

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        // Reuse the setters so the rules live in ONE place.
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        String cleaned = name.trim();
        if (cleaned.length() < 2 || cleaned.length() > 50) {
            throw new IllegalArgumentException("Name must be 2 to 50 characters.");
        }
        if (cleaned.indexOf('|') >= 0) {
            throw new IllegalArgumentException("Name cannot contain the | character (it is used in the save file).");
        }
        boolean hasLetter = false;
        for (int i = 0; i < cleaned.length(); i++) {
            if (Character.isLetter(cleaned.charAt(i))) {
                hasLetter = true;
                break;
            }
        }
        if (!hasLetter) {
            throw new IllegalArgumentException("Name must contain at least one letter.");
        }
        this.name = cleaned;
    }

    public void setAge(int age) {
        // A college system: school-leavers through late learners.
        if (age < 15 || age > 70) {
            throw new IllegalArgumentException("Age must be between 15 and 70.");
        }
        this.age = age;
    }
}
