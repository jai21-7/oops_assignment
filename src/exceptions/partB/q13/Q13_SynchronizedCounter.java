package exceptions.partB.q13;

/*
 * Synchronization
 * ---------------
 * PROBLEM: two threads update the SAME counter. Without synchronized,
 * updates can mix and the total can be WRONG.
 *
 * synchronized method = only ONE thread at a time can run it on this object.
 *
 * We increment 10000 times from two threads. Expected total = 20000.
 */

class Counter {
    int value = 0;

    synchronized void increment() {
        value++;
    }
}

class Adder extends Thread {
    Counter counter;

    Adder(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}

public class Q13_SynchronizedCounter {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Adder a1 = new Adder(c);
        Adder a2 = new Adder(c);
        a1.start();
        a2.start();
        a1.join();
        a2.join();
        System.out.println("Expected 20000, actual = " + c.value);
    }
}
