# Complete assignment walkthrough

This document solves both PDFs in the repo, question by question.

1. `Overloading_Object_Parameter (1).pdf` — Assignment 1 (Q1–Q24)
2. `Inheritance_Overriding_Method_Hiding.pdf` — Assignment 2 (Q1–Q15)

Every program already lives under `src/`. Compile and run one file with:

```bash
javac -d out src/partA/q01/Q01_Calculator.java
java -cp out partA.q01.Q01_Calculator
```

Or run everything: `bash scripts/run-all.sh`.

---

# Big ideas (read this first)

Java often uses **the same name for different jobs**. The compiler and the JVM decide *which* job to run using different rules.

## 1. Method overloading (compile time)

Same method name in **one class**, **different parameter list**.

Java looks at the **call site** while compiling:

- `add(10, 20)` → two `int`s
- `add(12.5, 7.3)` → two `double`s
- `add(5, 15, 25)` → three `int`s

Changing only the **return type** is not overloading. Changing only the **parameter name** is not overloading (`int side` and `int radius` are the same signature).

This is **compile-time polymorphism**.

## 2. Constructor overloading

A constructor:

- has the **same name as the class**
- has **no return type** (not even `void`)
- runs when you write `new ClassName(...)`

Several constructors with different parameter lists let you create objects with partial data.

If you write **any** constructor, Java does **not** invent `ClassName()` for you. Write a no-arg constructor yourself if you need `new ClassName()`.

`this.field = parameter` — `this` is the object being built.

`this(...)` — call **another constructor of the same class**. It must be the **first** statement. That is **constructor chaining**.

## 3. Object as a parameter

A method can take a whole object:

```java
s1.compare(s2);
```

- `s1` (before the dot) is `this` inside the method
- `s2` (in the brackets) is the parameter

Two styles used in this assignment:

| Style | What happens | Example |
| --- | --- | --- |
| Return a new object | originals stay unchanged | `Distance sum = d1.add(d2)` |
| Mutate `this` | current object changes | `n1.add(n2)` grows `n1.value` |

## 4. Inheritance (`extends`)

Child **IS-A** parent. Child gets parent fields and methods. Child constructor should call `super(...)` first so the parent part is filled.

| Kind | Picture | Where |
| --- | --- | --- |
| Single | Employee → Manager | Assignment 2 Q1 |
| Hierarchical | Vehicle → Car and Bike | Assignment 2 Q2, Q5 |
| Multilevel | Person → Student → Result | Assignment 2 Q4 |

## 5. Overriding vs hiding (the exam trap)

| | Overriding | Hiding |
| --- | --- | --- |
| Kind of method | instance (`void work()`) | static (`static void show()`) |
| Chosen by | **object** (`new Child()`) | **reference type** (`Parent p`) |
| When | runtime | compile time |
| `Parent p = new Child(); p.method();` | Child version | Parent version |

Memory hook:

- no `static` → follow the **object** (right side of `new`)
- `static` → follow the **left side** (the variable’s type)

`@Override` is optional. Use it: the compiler then proves you really overrode a parent instance method. You **cannot** put `@Override` on a hidden static method and expect it to behave like overriding.

---

# Assignment 1 — Overloading and object as parameter

## Part A — Method overloading

### Q1 Calculator — `add()`

**Ask:** add two ints, three ints, two doubles.

**Why three methods?** The name `add` is one idea. The *inputs* differ.

**Steps**

1. Class `Calculator` with three methods, all named `add`.
2. `int add(int a, int b)` → `a + b`. Different **count** from the three-int version.
3. `int add(int a, int b, int c)` → `a + b + c`.
4. `double add(double a, double b)` → same count as version 1, different **type**.
5. In `main`: `Calculator calc = new Calculator();`
6. Call `calc.add(10, 20)`, `calc.add(5, 15, 25)`, `calc.add(12.5, 7.3)`.

Writing `12.5` (a decimal) tells Java the arguments are `double`, so it will not pick `add(int, int)`.

**File:** `src/partA/q01/Q01_Calculator.java`

**Output**

```
Called: add(int, int)
10 + 20 = 30
Called: add(int, int, int)
5 + 15 + 25 = 45
Called: add(double, double)
12.5 + 7.3 = 19.8
```

---

### Q2 Area — `calculateArea()`

**Ask:** area of square, rectangle, circle.

Formulas: square `side * side`, rectangle `length * breadth`, circle `π * r * r`.

**The trap:** square and circle both need **one** `double`. Java cannot tell `calculateArea(double side)` from `calculateArea(double radius)`. Parameter *names* are erased at the signature level.

**Fix used here:** circle is `calculateArea(double radius, String shape)` so the signature is `(double, String)`, not `(double)`.

**Steps**

1. One `double` → square.
2. Two `double`s → rectangle.
3. Radius + `"circle"` → `Math.PI * radius * radius`.

**File:** `src/partA/q02/Q02_Area.java`

---

### Q3 Maximum — `max()`

**Ask:** max of 2 ints, 3 ints, 2 doubles.

**Steps**

1. Two ints: `if (a > b) return a; else return b;`
2. Three ints: reuse the 2-int `max` twice: `max(max(a, b), c)`. That is overloading *and* a method calling another overload.
3. Two doubles: same `if`, `double` return type.

`max(9.8, 9.81)` must use the double version. Integer comparison would be wrong if you truncated.

**File:** `src/partA/q03/Q03_Maximum.java`

---

### Q4 Student — `display()`

**Ask:** print name; name+marks; name+marks+grade.

Overloading here models “how much data we currently have,” without names like `displayNameMarksGrade()`.

Helper `gradeFromMarks(82)` → `'B'` (rule: ≥90 A, ≥75 B, ≥50 C, else F) so version 3 can be demonstrated.

**File:** `src/partA/q04/Q04_StudentDisplay.java`

---

### Q5 Printer — `print()`

**Ask:** print `int`, `double`, `char`, `String`.

This is how `System.out.println` itself is built: one name, many types.

Java matches by argument type:

| You write | Type |
| --- | --- |
| `42` | `int` |
| `3.14159` | `double` |
| `'J'` (single quotes) | `char` |
| `"Hello Java"` (double quotes) | `String` |

**File:** `src/partA/q05/Q05_Printer.java`

---

### Q6 Number — `multiply()`

Same overloading pattern as Q1. Practice the *shape* of overloading, not only addition.

- two ints: `6 * 7 = 42`
- three ints: `2 * 3 * 4 = 24`
- two doubles: `1.5 * 2.5 = 3.75`

**File:** `src/partA/q06/Q06_NumberMultiply.java`

---

## Part B — Constructor overloading

### Q7 Student constructors

**Ask:** init name; name+roll; name+roll+marks.

**Steps**

1. Fields: `String name`, `int roll`, `int marks`.
2. `Student(String name)` stores name, roll and marks = 0.
3. `Student(String name, int roll)` stores both, marks = 0.
4. `Student(String name, int roll, int marks)` stores all three.
5. Create three objects with `new` and print them.

`this.name = name` avoids a clash: the field and the parameter would otherwise be the same identifier.

**File:** `src/partB/q07/Q07_StudentConstructors.java`

---

### Q8 Employee constructors

Same three-level pattern: id; id+name; id+name+salary.

Missing pieces get placeholders: `"Not assigned"` and `0.0`.

**File:** `src/partB/q08/Q08_EmployeeConstructors.java`

---

### Q9 Rectangle constructors + area

**Ask:** default; one value (square); length and breadth. Print area.

**Steps**

1. `Rectangle()` → 1 × 1 (area defined, not 0×0 unless you prefer that).
2. `Rectangle(double side)` → length = breadth = side.
3. `Rectangle(double length, double breadth)` → normal rectangle.
4. `area()` returns `length * breadth`.

A square **is a** rectangle with equal sides, so one number is enough.

**File:** `src/partB/q09/Q09_RectangleConstructors.java`

---

### Q10 Box constructors + volume

Volume = `length * width * height`.

- no dimensions → all 0, volume 0
- one dimension → cube (all three equal): `4³ = 64`
- three dimensions → `2 * 3 * 5 = 30`

**File:** `src/partB/q10/Q10_BoxConstructors.java`

---

### Q11 Book constructors

Think of a library card:

- blank form → `Book()`
- title scribbled → `Book("Wings of Fire")`
- full catalogue card → title, author, price

**File:** `src/partB/q11/Q11_BookConstructors.java`

---

### Q12 Constructor chaining with `this()`

**Ask:** three overloaded Student constructors that call each other with `this()`.

`this()` must be the **first** line.

Chain used:

```
Student()
  → Student(name)             with "Unknown"
       → Student(name, roll)  with roll 0
            → Student(name, roll, marks)  with marks 0
```

Only the last constructor assigns fields. The others forward defaults.

**Why printed “Finished:” lines appear in reverse:** the full constructor runs, returns, then the caller constructor continues. So for `new Student()` you see:

1. Finished: Student(name, roll, marks)
2. Finished: Student(name, roll)
3. Finished: Student(name)
4. Finished: Student()

**File:** `src/partB/q12/Q12_StudentChaining.java`

---

## Part C — Object as a parameter

### Q13 Student `compare(Student s)`

**Ask:** compare marks of two students.

**Steps**

1. Fields: roll, marks.
2. Create `s1` and `s2`.
3. `void compare(Student s)`.
4. Compare `this.marks` vs `s.marks`. Print who is higher, or a tie.

`s1.compare(s2)` means: “from s1’s point of view, look at s2.”

**File:** `src/partC/q13/Q13_StudentCompare.java`

---

### Q14 Employee `compareSalary(Employee e)`

Same pattern as Q13, using `salary` and names (Ravi 35000 vs Sana 42000).

**File:** `src/partC/q14/Q14_EmployeeCompareSalary.java`

---

### Q15 Distance `add(Distance d)` returns a new Distance

**Ask:** add feet and inches.

**Carry rule:** 12 inches = 1 foot.

Example:

```
5 ft 8 in
+ 3 ft 10 in
= 8 ft 18 in
= 9 ft 6 in     because 18 = 1 foot + 6 inches
```

Implementation:

```
totalFeet   = this.feet + d.feet
totalInches = this.inches + d.inches
return new Distance(totalFeet, totalInches)  // constructor normalizes
```

`d1` and `d2` stay unchanged. The sum is a **third** object.

**File:** `src/partC/q15/Q15_DistanceAdd.java`

---

### Q16 Number `add(Number n)` changes the current object

```
this.value = this.value + n.value;
```

`n1.add(n2)`: n1 goes 10 → 17. n2 stays 7.

This is the opposite of Q15: **mutate this**, do not allocate a new Number.

**File:** `src/partC/q16/Q16_NumberAdd.java`

---

### Q17 Time `add(Time t)`

Hours and minutes. 60 minutes become 1 hour (same carry idea as inches).

`2:50 + 1:20 = 3:70 = 4:10`.

Returns a new `Time` (Q15 style).

**File:** `src/partC/q17/Q17_TimeAdd.java`

---

### Q18 Complex `add(Complex c)`

A complex number is `real + imag i`.

Add reals, add imaginaries: `(3+4i) + (1+2i) = 4+6i`.

Returns a new `Complex`. Originals unchanged.

**File:** `src/partC/q18/Q18_ComplexAdd.java`

---

## Part D — Combined

These mix constructor overloading, method overloading, and object parameters.

### Q19 Box constructors + `compare(Box b)`

Create boxes three ways (default 1×1×1, cube, length/width/height).

`volume = l * w * h`. Compare `this.volume()` with `b.volume()`.

Default vs cube 3: 1 vs 27. Cube vs carton 2×4×5: 27 vs 40.

**File:** `src/partD/q19/Q19_BoxCompare.java`

---

### Q20 Calculator `add` ints, doubles, and two Number objects

`add(Number a, Number b)` uses `a.value + b.value`.

Java still picks the method by **types you pass**:

- `c.add(4, 6)` → ints
- `c.add(2.5, 3.5)` → doubles
- `c.add(n1, n2)` → objects

This is overloading **plus** object as parameter.

**File:** `src/partD/q20/Q20_CalculatorNumbers.java`

---

### Q21 BankAccount transfer

Constructor overloading to open an account (id / id+name / full).

`a1.transfer(a2, 700)` means: take 700 from `a1` (`this`) and give it to `a2` (`b`).

Steps inside `transfer`:

1. Reject amount ≤ 0.
2. If `this.balance < amount`, print failed (Diya trying 5000 fails).
3. Else subtract from this, add to b.

2000 − 700 = 1300 on Arjun; 500 + 700 = 1200 on Diya.

**File:** `src/partD/q21/Q21_BankAccountTransfer.java`

---

### Q22 Product constructors + `comparePrice`

Open a product with name only, name+price, or full details (stock too).

Then compare `this.price` with `p.price` (Pen 20 vs Bag 799).

**File:** `src/partD/q22/Q22_ProductComparePrice.java`

---

### Q23 Rectangle constructors + `compareArea`

Same idea as Q9 + Q19, using area instead of volume. Square 6×6 = 36 vs 8×3 = 24.

**File:** `src/partD/q23/Q23_RectangleCompareArea.java`

---

### Q24 Library book — all three skills

Real-life example: college library.

1. **Constructors** — blank book, title only, full details, using `this()` chaining.
2. **Overloaded `issue`** — guest / named student / several copies.
3. **`receiveFrom(LibraryBook other)`** — move leftover copies from another object into this one (other goes to 0).

`main` is labelled STEP 1, 2, 3. After issuing 1+1+2 from 5 copies, 1 remains; merging 1 old-stock copy yields 2.

**File:** `src/partD/q24/Q24_LibraryBookExample.java`

---

# Assignment 2 — Inheritance, overriding, method hiding

## Part A — Inheritance

### Q1 Employee → Manager (single inheritance)

Parent holds `name` and `salary` plus `displayEmployee()`. Child adds `department` plus `displayManager()`.

`Manager` constructor calls `super(name, salary)` first, then sets department.

A Manager object **is an** Employee, so both displays work on one object (Kavya, 75000, Sales).

**File:** `src/inheritance/partA/q01/Q01_EmployeeManager.java`

---

### Q2 Vehicle → Car, Bike (hierarchical)

Shared: `brand`, `speed`, `displayVehicle()`.

- Car adds `numberOfDoors`
- Bike adds `engineCapacity`

Two children of one parent. Honda car 4 doors; Yamaha bike 155 cc.

**File:** `src/inheritance/partA/q02/Q02_VehicleCarBike.java`

---

### Q3 BankAccount → SavingsAccount, CurrentAccount

Shared: `accountNumber`, `holderName`, `balance`, `deposit()`, `withdraw()`, `displayBalance()`.

**Extra features (what the question asked)**

- **Savings:** `addInterest()` at 5% on 10000 → +500 → 10500.
- **Current:** overdraft 1000. Withdraw 2500 from 2000 succeeds (balance −500). Next withdraw 2000 fails (would exceed overdraft).

Current **overrides** `withdraw()` because the rule is different. Savings reuses the parent withdraw.

**File:** `src/inheritance/partA/q03/Q03_BankAccounts.java`

---

### Q4 Person → Student → Result (multilevel)

```
Person (name, age)
  └── Student (roll, course)
        └── Result (three marks)
```

One `Result` object contains the whole chain. Constructor cascade:

`Result(...)` → `super(...)` Student → `super(...)` Person.

Marks 80, 75, 90 → total 245, percentage `245 / 3.0`.

**File:** `src/inheritance/partA/q04/Q04_PersonStudentResult.java`

---

### Q5 Shape → Circle, Rectangle, Triangle

Parent only has `display()`. Each child has its own data and `area()`.

- Circle r=7 → πr²
- Rectangle 8×4 → 32
- Triangle base 6 height 5 → ½bh = 15

Hierarchical inheritance again: three children, one parent.

**File:** `src/inheritance/partA/q05/Q05_Shapes.java`

---

## Part B — Method overriding

**Overriding** = child writes a method with the **same name, parameters, and return type** as the parent, replacing behaviour.

**Overloading vs overriding**

| | Overloading | Overriding |
| --- | --- | --- |
| Where | same class | child class |
| Parameters | must differ | must match |
| When chosen | compile time | runtime (instance methods) |

### Q6 Animal → Dog, Cat, Cow

Override `sound()`. Each object prints its own sound (Woof / Meow / Moo).

**File:** `src/inheritance/partB/q06/Q06_AnimalSounds.java`

---

### Q7 Bank → SBI, HDFC, ICICI (runtime polymorphism)

```java
Bank b = new SBI();
b.getRateOfInterest();  // 6.5, SBI's method
```

For **instance** methods, Java uses the **object** (SBI), not the reference type (Bank). Decision happens at **runtime**. That is **dynamic method dispatch**.

Same `b` variable is reused for HDFC (7.2) and ICICI (7.0).

**File:** `src/inheritance/partB/q07/Q07_BankInterest.java`

---

### Q8 Employee salaries

`Employee e` points at different objects; `calculateSalary()` is overridden:

| Class | Rule | Demo |
| --- | --- | --- |
| FullTimeEmployee | monthly salary | 40000 |
| PartTimeEmployee | hours × rate | 160 × 100 = 16000 |
| ContractEmployee | fixed amount | 25000 |

**File:** `src/inheritance/partB/q08/Q08_EmployeeSalary.java`

---

### Q9 Payment methods

`Payment p` + `makePayment()`:

- Credit card
- UPI
- Net banking

Same call `p.makePayment()`, three bodies.

**File:** `src/inheritance/partB/q09/Q09_Payments.java`

---

### Q10 Transport fares

`Transport t` + `calculateFare(10)`:

| Class | Rule | For 10 km |
| --- | --- | --- |
| Bus | 5 / km | 50 |
| Train | 3 / km | 30 |
| Taxi | 20 + 12 / km | 140 |

**File:** `src/inheritance/partB/q10/Q10_TransportFare.java`

---

## Part C — Method hiding (static)

### Q11 Parent / Child `static show()`

```java
Parent p = new Child();
Child  c = new Child();
p.show();  // Parent static show()
c.show();  // Child static show()
```

`show()` is static. Java uses the **reference type**. The object `new Child()` is ignored for static methods.

**File:** `src/inheritance/partC/q11/Q11_StaticHiding.java`

---

### Q12 instance `display()` + static `show()` (the comparison question)

Same two objects: `Parent p = new Child();` and `Child c = new Child();`

| Call | Kind | Chosen by | Result |
| --- | --- | --- | --- |
| `p.display()` | instance | object (Child) | Child display |
| `c.display()` | instance | object (Child) | Child display |
| `p.show()` | static | reference (Parent) | Parent show |
| `c.show()` | static | reference (Child) | Child show |

**Overridden:** `display()`  
**Hidden:** `show()`

**File:** `src/inheritance/partC/q12/Q12_OverrideVsHide.java`

---

### Q13 GrandParent → Parent → Child

All three `show()` methods are static. Each object is `new Child()`, but:

**Prediction (and actual output)**

- `GrandParent g = new Child(); g.show();` → GrandParent
- `Parent p = new Child(); p.show();` → Parent
- `Child c = new Child(); c.show();` → Child

Hiding is **not** “use the most specific class.” It is “use the type of the variable.”

**File:** `src/inheritance/partC/q13/Q13_ThreeLevelHiding.java`

---

### Q14 Employee `work()` vs `companyPolicy()`

`Employee e = new Manager();` and `Manager m = new Manager();`

- `work()` instance → **Manager** version through **both** `e` and `m` (overriding).
- `companyPolicy()` static → Employee version through `e`, Manager version through `m` (hiding).

Why they differ: one is bound to the object at runtime; one is bound to the reference at compile time.

**File:** `src/inheritance/partC/q14/Q14_EmployeeManagerHide.java`

---

### Q15 Shopping system — Product → ElectronicProduct → Mobile

Product has `name`, `price`, instance `displayDetails()`, static `category()`.

Mobile **overrides** `displayDetails()` and **hides** `category()`.

`Product p = new Mobile("Nova X", 24999, "Acme", 8);`  
`Mobile m = new Mobile(...)` (same data).

Exam answers the PDF asked for:

1. **Overriding** = `displayDetails()` — `p` and `m` both print Mobile details.
2. **Hiding** = `category()` — `p` prints “General Product”, `m` prints “Mobile Phones”.
3. **Output differs** because instance methods use the **object**, static methods use the **reference type**.
4. **Compile time** = which static `category()` to call (from the reference); also whether the call is legal on that type.
5. **Runtime** = which `displayDetails()` body actually runs (from the Mobile object).

**File:** `src/inheritance/partC/q15/Q15_ShoppingSystem.java`

---

# How to remember this for a viva or exam

1. **Overloading** — same name, different parameters, **same class**, compiler decides.
2. **Constructor overloading** — different `new ClassName(...)` shapes. `this()` chains constructors; `super()` fills the parent.
3. **Object as parameter** — `this` vs the argument. Decide: new object, or mutate this?
4. **Inheritance** — reuse + IS-A. Single / hierarchical / multilevel.
5. **Override** = instance, follow the **object**. **Hide** = static, follow the **reference**.

If a question shows `Parent p = new Child();` always ask: *is the method static?* That one question splits Q11–Q15 from Q6–Q10.
