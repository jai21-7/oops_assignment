package exceptions.partB.q11;

/*
 * Lab: two threads — one prints 1..50, the other prints 50..1.
 *
 * join() waits until that thread FINISHES, so main does not exit too early
 * (and so the demo output is complete).
 */

class Forward extends Thread {
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n[forward done]");
    }
}

class Reverse extends Thread {
    public void run() {
        for (int i = 50; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println("\n[reverse done]");
    }
}

public class Q11_TwoThreadsCount {
    public static void main(String[] args) throws InterruptedException {
        Forward f = new Forward();
        Reverse r = new Reverse();
        f.start();
        r.start();
        f.join();
        r.join();
        System.out.println("main: both threads finished.");
    }
}
