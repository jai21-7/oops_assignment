package exceptions.partB.q12;

/*
 * Thread priority
 * ---------------
 * Java stores a number 1..10 (MIN=1, NORM=5, MAX=10).
 * Higher number = more chance to get CPU time.
 * It is a HINT to the scheduler, not a guarantee of order.
 *
 * setPriority(int)
 * getPriority()
 *
 * sleep(ms) pauses THIS thread so others can run — used here so both
 * workers print something before main ends.
 */

class PriWork extends Thread {
    PriWork(String name, int priority) {
        setName(name);
        setPriority(priority);
    }

    public void run() {
        System.out.println(getName() + " running with priority " + getPriority());
    }
}

public class Q12_ThreadPriority {
    public static void main(String[] args) throws InterruptedException {
        PriWork low = new PriWork("Low", Thread.MIN_PRIORITY);
        PriWork high = new PriWork("High", Thread.MAX_PRIORITY);
        System.out.println("MIN=" + Thread.MIN_PRIORITY + " NORM=" + Thread.NORM_PRIORITY
                + " MAX=" + Thread.MAX_PRIORITY);
        low.start();
        high.start();
        low.join();
        high.join();
    }
}
