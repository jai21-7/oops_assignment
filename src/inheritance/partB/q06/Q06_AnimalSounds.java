package inheritance.partB.q06;

/*
 * QUESTION 6 — Method overriding
 * ------------------------------
 * Animal.sound() is a GENERAL version.
 * Dog, Cat, Cow each WRITE THEIR OWN sound() with the SAME signature.
 *
 * THAT IS OVERRIDING:
 *   same name, same parameters, same return type
 *   in a CHILD class
 *   replacing the parent version for that child
 *
 * DIFFERENT FROM OVERLOADING:
 *   Overloading = same class, different parameters.
 *   Overriding  = child class, SAME parameters.
 *
 * When you call dog.sound(), Java runs Dog's version, not Animal's.
 */

class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks: Woof");
    }
}

class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows: Meow");
    }
}

class Cow extends Animal {
    @Override
    void sound() {
        System.out.println("Cow moos: Moo");
    }
}

public class Q06_AnimalSounds {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();
        Cow cow = new Cow();

        dog.sound();
        cat.sound();
        cow.sound();
    }
}
