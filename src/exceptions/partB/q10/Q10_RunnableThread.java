package exceptions.partB.q10;

/*
 * Create a thread by implementing Runnable.
 *
 * WHY Runnable?
 *   A class can extend only ONE class. If it already extends something else,
 *   it can still implement Runnable.
 *
 *   Thread t = new Thread(runnableObject);
 *   t.start();
 */

class MessageJob implements Runnable {
    private final String message;

    MessageJob(String message) {
        this.message = message;
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + message + " (" + i + ")");
        }
    }
}

public class Q10_RunnableThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MessageJob("hello"), "T1");
        Thread t2 = new Thread(new MessageJob("world"), "T2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
