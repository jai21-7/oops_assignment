package exceptions.partB.q15;

/*
 * Deadlock (explained, not frozen)
 * --------------------------------
 * Deadlock = two threads each hold a lock the other needs, forever.
 *
 *   T1 locks A, wants B
 *   T2 locks B, wants A
 *
 * We do NOT freeze run-all.sh. Instead we:
 *   1. Show the BAD order in comments.
 *   2. Use the SAME lock order in both threads so they finish.
 *
 * Rule to remember: always lock A then B, never the reverse.
 */

public class Q15_DeadlockExplained {
    public static void main(String[] args) throws InterruptedException {
        Object lockA = new Object();
        Object lockB = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                synchronized (lockB) {
                    System.out.println("T1 got A then B");
                }
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            // SAME order as T1 (A then B) — this avoids deadlock.
            // BAD version would be: synchronized(lockB) { synchronized(lockA) { ... } }
            synchronized (lockA) {
                synchronized (lockB) {
                    System.out.println("T2 got A then B");
                }
            }
        }, "T2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Finished because both threads lock A then B.");
        System.out.println("If T2 locked B then A, they could wait forever = deadlock.");
    }
}
