# Part A — Method (Function) Overloading

Java lets you write **several methods with the same name** in one class,
as long as their **parameter lists are different**. That is method overloading.

The compiler looks at the call and picks the matching version. This is
**compile-time polymorphism**.

Rules that beginners mix up:

- Changing only the **return type** is NOT overloading.
- Changing only the **parameter names** is NOT overloading (`int side` vs `int radius` is the same).
- What counts: **number** of parameters, **types**, or **order** of types.

How to run one program (from the project folder):

```bash
javac -d out src/partA/q01/Q01_Calculator.java
java -cp out partA.q01.Q01_Calculator
```

Each question lives in its own package (`partA.q01`, `partA.q02`, ...) so class
names like `Student` can be reused in later questions without clashing.

---

## Q1 Calculator — `add()`

**Ask:** add two ints, three ints, two doubles.

**Steps**

1. Create class `Calculator`.
2. Write `int add(int a, int b)` → `return a + b`.
3. Write `int add(int a, int b, int c)` → three numbers, so a different count.
4. Write `double add(double a, double b)` → same count as version 1, but `double` type.
5. In `main`, create `Calculator calc = new Calculator();`
6. Call `calc.add(10, 20)`, `calc.add(5, 15, 25)`, `calc.add(12.5, 7.3)`.

Java matches each call by looking at the arguments.

---

## Q2 Area — `calculateArea()`

**Ask:** area of square, rectangle, circle.

Formulas: square `side*side`, rectangle `length*breadth`, circle `π*r*r`.

**Trap:** square and circle both need **one** `double`. Java cannot tell
`calculateArea(double side)` from `calculateArea(double radius)`.

**Fix used here:** circle takes `(double radius, String shape)`.

**Steps**

1. One-parameter method → square.
2. Two-double method → rectangle.
3. Radius + `"circle"` → circle using `Math.PI`.

---

## Q3 Maximum — `max()`

**Ask:** max of 2 ints, 3 ints, 2 doubles.

**Steps**

1. Two ints: `if (a > b) return a; else return b;`
2. Three ints: first find max of a and b, then compare that with c.
   You can even call the 2-int `max` from inside the 3-int `max`.
3. Two doubles: same `if`, but `double` return type.

---

## Q4 Student — `display()`

**Ask:** print name; name+marks; name+marks+grade.

**Steps**

1. `display(String name)` prints one line.
2. `display(String name, int marks)` prints two lines.
3. `display(String name, int marks, char grade)` prints three lines.
4. A helper `gradeFromMarks` turns 82 into `'B'` so you can demo version 3.

---

## Q5 Printer — `print()`

**Ask:** print int, double, char, String.

**Steps**

1. Four methods, all named `print`, each taking one different type.
2. Call them with `42`, `3.14159`, `'J'` (single quotes), `"Hello Java"` (double quotes).

This is how `System.out.println` itself works inside Java.

---

## Q6 Number — `multiply()`

**Ask:** multiply two ints, three ints, two doubles.

Same overloading pattern as Q1. Practice the pattern, not only the math.
