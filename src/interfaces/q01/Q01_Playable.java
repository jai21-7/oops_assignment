package interfaces.q01;

/*
 * WHAT IS AN INTERFACE? (beginner)
 *   An interface is a CONTRACT: a list of method names a class PROMISES to write.
 *
 *   interface Playable { void play(); }
 *   class Guitar implements Playable { public void play() { ... } }
 *
 *   "implements" = "I will write every method from that interface."
 *
 * WHY?
 *   Different objects (Guitar, Piano) can be used the SAME way: play().
 *   A class can implement MANY interfaces (Java has no multiple class inheritance).
 *
 * Rules:
 *   - methods in a classic interface are public abstract (no body)
 *   - the class MUST use public when it writes those methods
 */

interface Playable {
    void play();
}

class Guitar implements Playable {
    public void play() {
        System.out.println("Guitar: strumming chords");
    }
}

class Piano implements Playable {
    public void play() {
        System.out.println("Piano: playing notes");
    }
}

public class Q01_Playable {
    public static void main(String[] args) {
        Playable g = new Guitar();
        Playable p = new Piano();
        g.play();
        p.play();
    }
}
