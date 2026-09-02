# Part B — Constructor Overloading

A **constructor** runs when you write `new ClassName(...)`.
Its name is the class name. It has **no return type**.

**Constructor overloading** = several constructors, different parameter lists.

If you write any constructor yourself, Java does **not** add a default
`ClassName()` for you. Write it if you need `new ClassName()`.

`this.field = parameter` — `this` means “this object’s field”.

How to run (from the project folder):

```bash
javac -d out src/partB/q07/Q07_StudentConstructors.java
java -cp out partB.q07.Q07_StudentConstructors
```

---

## Q7 Student constructors

**Ask:** init name; name+roll; name+roll+marks.

**Steps**

1. Fields: `String name`, `int roll`, `int marks`.
2. `Student(String name)` stores name, sets roll and marks to 0.
3. `Student(String name, int roll)` stores both, marks = 0.
4. `Student(String name, int roll, int marks)` stores all three.
5. In `main`, create three objects with `new` and print them.

---

## Q8 Employee constructors

**Ask:** id; id+name; id+name+salary.

Same three-level pattern as Q7 with different fields.

---

## Q9 Rectangle constructors + area

**Ask:** default; one value (square); length and breadth. Print area.

**Steps**

1. `Rectangle()` → 1 x 1 (or any default you choose).
2. `Rectangle(double side)` → length = breadth = side.
3. `Rectangle(double length, double breadth)` → normal rectangle.
4. `area()` returns `length * breadth`.

---

## Q10 Box constructors + volume

**Ask:** no dimensions; one dimension (cube); length, width, height. Print volume.

Volume = `length * width * height`.
Cube: all three sides equal. Empty box: all zeros, volume 0.

---

## Q11 Book constructors

**Ask:** no args; title only; title + author + price.

Think of a library card: blank, title scribbled, or fully filled.

---

## Q12 Constructor chaining with `this()`

**Ask:** three overloaded Student constructors that call each other with `this()`.

**What `this()` does:** call another constructor **in the same class**.
It must be the **first** line of the constructor.

**Chain used here**

```
Student()
  -> Student(name)             with "Unknown"
       -> Student(name, roll)  with roll 0
            -> Student(name, roll, marks)  with marks 0
```

Only the last constructor assigns the fields. The others just forward defaults.

Watch the printed “Finished:” lines: the **full** constructor prints first,
because it runs before the caller constructor continues.
