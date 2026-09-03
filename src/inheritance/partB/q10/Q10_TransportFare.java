package inheritance.partB.q10;

/*
 * QUESTION 10 — Dynamic method dispatch with a parameter
 * ------------------------------------------------------
 * Transport.calculateFare(int distance) is overridden.
 *
 * Fare rules we define:
 *   Bus  : 5 rupees per km
 *   Train: 3 rupees per km
 *   Taxi : 20 rupees base + 12 rupees per km
 *
 * DYNAMIC METHOD DISPATCH:
 *   The call t.calculateFare(10) is bound to Bus/Train/Taxi
 *   at RUNTIME from the object sitting in t.
 */

class Transport {
    double calculateFare(int distance) {
        return 0;
    }
}

class Bus extends Transport {
    @Override
    double calculateFare(int distance) {
        return 5 * distance;
    }
}

class Train extends Transport {
    @Override
    double calculateFare(int distance) {
        return 3 * distance;
    }
}

class Taxi extends Transport {
    @Override
    double calculateFare(int distance) {
        return 20 + 12 * distance;
    }
}

public class Q10_TransportFare {
    public static void main(String[] args) {
        int km = 10;
        Transport t;

        t = new Bus();
        System.out.println("Bus  for " + km + " km = " + t.calculateFare(km));

        t = new Train();
        System.out.println("Train for " + km + " km = " + t.calculateFare(km));

        t = new Taxi();
        System.out.println("Taxi for " + km + " km = " + t.calculateFare(km));
    }
}
