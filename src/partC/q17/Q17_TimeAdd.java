package partC.q17;

/*
 * QUESTION 17
 * ---------
 * Time has hours and minutes.
 * add(Time t) adds two Time objects and displays hours and minutes.
 *
 * BEGINNER IDEA:
 *   60 minutes = 1 hour.
 *   2h 50min + 1h 20min = 3h 70min = 4h 10min
 *
 * We return a new Time (like Q15) and also print it.
 */

class Time {
    int hours;
    int minutes;

    Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
        normalize();
    }

    void normalize() {
        if (minutes >= 60) {
            hours = hours + minutes / 60;
            minutes = minutes % 60;
        }
    }

    Time add(Time t) {
        int h = this.hours + t.hours;
        int m = this.minutes + t.minutes;
        return new Time(h, m);
    }

    void show(String label) {
        System.out.println(label + ": " + hours + " hours " + minutes + " minutes");
    }
}

public class Q17_TimeAdd {
    public static void main(String[] args) {
        Time t1 = new Time(2, 50);
        Time t2 = new Time(1, 20);

        t1.show("Start");
        t2.show("Extra");

        Time total = t1.add(t2);
        total.show("Total");
    }
}
