package interfaces.q05;

/*
 * Interface vs abstract class (what beginners mix up)
 * ---------------------------------------------------
 * Abstract class:
 *   - can have instance fields, constructors, mixed abstract + normal methods
 *   - a class extends only ONE abstract class
 *
 * Interface:
 *   - historically: only method signatures (+ constants)
 *   - a class can implement MANY interfaces
 *
 * Use abstract class when objects SHARE code/data.
 * Use interface when you only need a shared CAPABILITY (pay, play, draw).
 */

abstract class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    void eat() {
        System.out.println(name + " eats food");
    }

    abstract void speak();
}

interface Pet {
    void beFriendly();
}

class Dog extends Animal implements Pet {
    Dog(String name) {
        super(name);
    }

    public void speak() {
        System.out.println(name + " barks");
    }

    public void beFriendly() {
        System.out.println(name + " wags tail");
    }
}

public class Q05_AbstractVsInterface {
    public static void main(String[] args) {
        Dog d = new Dog("Bruno");
        d.eat();
        d.speak();
        d.beFriendly();
    }
}
