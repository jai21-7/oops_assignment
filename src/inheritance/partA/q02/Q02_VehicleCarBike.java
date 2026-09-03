package inheritance.partA.q02;

/*
 * QUESTION 2 — Inheritance (one parent, two children)
 * ---------------------------------------------------
 * Vehicle  : brand, speed, displayVehicle()
 * Car      : numberOfDoors
 * Bike     : engineCapacity
 *
 * BEGINNER IDEA:
 *   Two different subclasses can extend the SAME superclass.
 *   Car and Bike both ARE Vehicles, but they keep extra data of their own.
 *
 *   Vehicle
 *     ├── Car   (doors)
 *     └── Bike  (cc)
 */

class Vehicle {
    String brand;
    int speed;

    Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    void displayVehicle() {
        System.out.println("Brand : " + brand);
        System.out.println("Speed : " + speed + " km/h");
    }
}

class Car extends Vehicle {
    int numberOfDoors;

    Car(String brand, int speed, int numberOfDoors) {
        super(brand, speed);
        this.numberOfDoors = numberOfDoors;
    }

    void displayCar() {
        displayVehicle();
        System.out.println("Doors : " + numberOfDoors);
    }
}

class Bike extends Vehicle {
    int engineCapacity;

    Bike(String brand, int speed, int engineCapacity) {
        super(brand, speed);
        this.engineCapacity = engineCapacity;
    }

    void displayBike() {
        displayVehicle();
        System.out.println("Engine: " + engineCapacity + " cc");
    }
}

public class Q02_VehicleCarBike {
    public static void main(String[] args) {
        Car car = new Car("Honda", 180, 4);
        Bike bike = new Bike("Yamaha", 140, 155);

        System.out.println("--- Car ---");
        car.displayCar();
        System.out.println();
        System.out.println("--- Bike ---");
        bike.displayBike();
    }
}
