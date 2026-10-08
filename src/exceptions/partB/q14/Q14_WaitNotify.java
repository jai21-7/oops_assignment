package exceptions.partB.q14;

/*
 * Inter-thread communication: wait() and notify()
 * -----------------------------------------------
 * Producer puts a number into a shared box.
 * Consumer takes it.
 * They take turns: produce, consume, produce, consume...
 *
 * wait()    = release the lock and sleep until notify()
 * notify()  = wake one waiting thread
 * Both MUST be called inside synchronized (same object).
 */

class Box {
    int value;
    boolean full = false;

    synchronized void put(int n) throws InterruptedException {
        while (full) {
            wait();
        }
        value = n;
        full = true;
        System.out.println("Produced " + n);
        notify();
    }

    synchronized int take() throws InterruptedException {
        while (!full) {
            wait();
        }
        full = false;
        System.out.println("Consumed " + value);
        notify();
        return value;
    }
}

public class Q14_WaitNotify {
    public static void main(String[] args) throws InterruptedException {
        Box box = new Box();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    box.put(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    box.take();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "consumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
