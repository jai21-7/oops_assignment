# Part A — Inheritance

**Inheritance** = a child class reuses a parent class with `extends`.

```java
class Manager extends Employee { ... }
```

- Child **IS-A** parent (`Manager` is an `Employee`).
- Child gets parent fields and methods.
- `super(...)` in the child constructor fills the parent part first.
- `super.method()` can call the parent version of a method.

Kinds you will see:

| Kind | Picture | This part |
| --- | --- | --- |
| Single | Employee → Manager | Q1 |
| Hierarchical | Vehicle → Car and Bike | Q2, Q5 |
| Multilevel | Person → Student → Result | Q4 |

Run one program:

```bash
javac -d out src/inheritance/partA/q01/Q01_EmployeeManager.java
java -cp out inheritance.partA.q01.Q01_EmployeeManager
```

---

## Q1 Employee → Manager

Parent holds name and salary. Child adds department. Create a Manager and print both employee and manager details.

## Q2 Vehicle → Car, Bike

Shared: brand, speed. Car adds doors. Bike adds engine cc.

## Q3 BankAccount → SavingsAccount, CurrentAccount

Shared: deposit, withdraw, displayBalance.
Savings extra: add interest.
Current extra: overdraft.

## Q4 Person → Student → Result

Three levels. One Result object contains the whole chain. Print total and percentage of three marks.

## Q5 Shape → Circle, Rectangle, Triangle

Each child has its own data and `area()`. Parent only has `display()`.
