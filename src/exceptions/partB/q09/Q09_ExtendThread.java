package exceptions.partB.q09;

/*
 * WHAT IS A THREAD? (beginner)
 *   A thread is a SMALL WORKER inside your program.
 *   main() itself is a thread. Extra threads can run AT THE SAME TIME
 *   (or appear to, by taking turns very fast).
 *
 * TWO WAYS TO CREATE A THREAD
 *   1. extend Thread and override run()     <- this file
 *   2. implement Runnable                   <- next file
 *
 * start()  = ask Java to create a real thread and call run()
 * run()    = the work (if you call run() yourself, it is NOT a new thread)
 *
 * NEVER call the deprecated stop()/suspend()/resume() methods.
 */

class NumberThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread " + getName() + " prints " + i);
        }
    }
}

public class Q09_ExtendThread {
    public static void main(String[] args) {
        NumberThread t = new NumberThread();
        t.setName("Worker-A");
        t.start(); // not t.run()
        System.out.println("main started the worker and continues...");
        try {
            t.join(); // wait so the demo always finishes printing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
