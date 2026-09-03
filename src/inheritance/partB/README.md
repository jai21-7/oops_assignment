# Part B — Method Overriding

**Overriding** = a child writes a method with the **same name, parameters, and return type** as the parent, to replace the parent’s behaviour.

```java
class Dog extends Animal {
    @Override
    void sound() { System.out.println("Woof"); }
}
```

`@Override` is optional but useful: the compiler checks that you really overrode something.

**Overloading vs overriding**

| | Overloading | Overriding |
| --- | --- | --- |
| Where | same class | child class |
| Parameters | must differ | must match |
| When chosen | compile time | runtime (for instance methods) |

**Runtime polymorphism / dynamic method dispatch**

```java
Bank b = new SBI();
b.getRateOfInterest();  // runs SBI's method
```

For **instance** methods, Java uses the **object** (SBI), not the reference type (Bank).

```bash
javac -d out src/inheritance/partB/q06/Q06_AnimalSounds.java
java -cp out inheritance.partB.q06.Q06_AnimalSounds
```

---

## Q6 Animal → Dog, Cat, Cow

Override `sound()`. Each object prints its own sound.

## Q7 Bank → SBI, HDFC, ICICI

Override interest rate. Use `Bank b = new SBI();` and so on.

## Q8 Employee salaries

FullTime monthly, PartTime hours×rate, Contract fixed. Call through `Employee e`.

## Q9 Payment methods

Credit card, UPI, net banking. Call `makePayment()` through `Payment p`.

## Q10 Transport fares

Bus 5/km, Train 3/km, Taxi 20 + 12/km. `Transport t` + `calculateFare(10)`.
