package sms.model;

/*
 * STEP 3b — Attendance belongs to one student
 * -------------------------------------------
 * classesHeld    = how many classes were conducted
 * classesAttended = how many of those the student was present for
 *
 * The percentage is not stored. It is calculated when you ask for it.
 * Storing it as well would create two copies that can disagree.
 * One source of truth: the two counts. The percentage is derived.
 *
 * Shortage rule used here: below 75% once at least one class has been held.
 */

public class Attendance {

    private int classesHeld;
    private int classesAttended;

    public Attendance() {
        this.classesHeld = 0;
        this.classesAttended = 0;
    }

    public int getClassesHeld() {
        return classesHeld;
    }

    public int getClassesAttended() {
        return classesAttended;
    }

    /**
     * Replace both totals at once. Checking them together matters:
     * attended can never be larger than held.
     */
    public void setTotals(int classesHeld, int classesAttended) {
        if (classesHeld < 0 || classesAttended < 0) {
            throw new IllegalArgumentException("Attendance counts cannot be negative.");
        }
        if (classesAttended > classesHeld) {
            throw new IllegalArgumentException("Classes attended cannot be more than classes held.");
        }
        this.classesHeld = classesHeld;
        this.classesAttended = classesAttended;
    }

    /** One class happened. present = true means the student was there. */
    public void recordClass(boolean present) {
        classesHeld = classesHeld + 1;
        if (present) {
            classesAttended = classesAttended + 1;
        }
    }

    public double percentage() {
        if (classesHeld == 0) {
            return 0.0;
        }
        return (classesAttended * 100.0) / classesHeld;
    }

    public boolean isShortage() {
        return classesHeld > 0 && percentage() < 75.0;
    }
}
