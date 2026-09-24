# OOPS Combined — slide-by-slide explanation

Source the filename points at: `OOPS Combined - Only for Ashu Bhai.pdf`  
(local path: `sem3/oops`). That file is a **combined lecture** of the two PDFs already in this repo:

1. `Overloading_Object_Parameter (1).pdf`
2. `Inheritance_Overriding_Method_Hiding.pdf`

This note explains the **same slides**, in teaching order: what the slide is saying, why Java works that way, a tiny example, and the exam trap. Matching programs are under `src/`.

---

# Unit 0 — Picture of OOP (what the title slide is for)

Procedural code is “a list of functions that poke data.”  
**Object-oriented** code is “data and the functions that belong to it, glued together as objects.”

Four pillars you must be able to say in one line each:

| Pillar | One line | Java hint |
| --- | --- | --- |
| **Encapsulation** | Hide data, expose behaviour | `private` fields + methods |
| **Abstraction** | Show the idea, hide the messy details | abstract class / interface |
| **Inheritance** | Child reuses parent (`IS-A`) | `extends` |
| **Polymorphism** | One name, many forms | overloading **or** overriding |

This combined PPT is **not** all four pillars equally. It drills **polymorphism** (overloading + overriding) and **inheritance**, plus **passing objects into methods**.

Two clocks in Java:

- **Compile time** — the compiler looks at the **source text** (how many arguments, which types, which variable type).
- **Runtime** — the JVM looks at the **actual object** created with `new`.

Almost every viva question on this PPT is: *which clock decided this call?*

---

# Part 1 — Method overloading (compile-time polymorphism)

**Slide idea:** several methods, **same name**, **same class**, **different parameter list**.

What counts as “different”:

1. Number of parameters (`add(int,int)` vs `add(int,int,int)`)
2. Types (`add(int,int)` vs `add(double,double)`)
3. Order of types (`f(int, String)` vs `f(String, int)`)

What does **not** count:

- Only the **return type** (`int add()` vs `double add()` with the same parameters) — compile error.
- Only the **parameter name** (`double side` vs `double radius`) — Java sees one `double`. Names are for humans.

The compiler matches the **call**. That is why this is compile-time polymorphism.

### Q1 Calculator — `add`

Three `add` methods: 2 ints, 3 ints, 2 doubles.

```
calc.add(10, 20);       // int, int     → 30
calc.add(5, 15, 25);    // three ints   → 45
calc.add(12.5, 7.3);    // two doubles  → 19.8
```

`12.5` has a decimal point, so it is a `double`. Java will not pick `add(int,int)`.

**File:** `src/partA/q01/Q01_Calculator.java`

### Q2 Area — the signature trap (this is the hard slide)

Square needs one number. Circle needs one number.  
`calculateArea(double side)` and `calculateArea(double radius)` are **the same method** to Java.

Fix used in the program: circle is `(double radius, String shape)` so the signature is different.

Formulas: square `side*side`, rectangle `l*b`, circle `πr²`.

**File:** `src/partA/q02/Q02_Area.java`

### Q3 Maximum — overload + reuse

2 ints, 3 ints, 2 doubles. The 3-int version can call the 2-int version:

`max(a,b,c)` = `max(max(a,b), c)`

Doubles must stay doubles (`9.8` vs `9.81`). If you cut them to `int`, you lose the difference.

**File:** `src/partA/q03/Q03_Maximum.java`

### Q4 Student `display`

Same name, more data each time: name → name+marks → name+marks+grade.  
That is overloading used as “print whatever we currently know.”

**File:** `src/partA/q04/Q04_StudentDisplay.java`

### Q5 Printer

`print(int)`, `print(double)`, `print(char)`, `print(String)`.  
This is how `System.out.println` itself is written. Quotes matter: `'J'` is `char`, `"Hello"` is `String`.

**File:** `src/partA/q05/Q05_Printer.java`

### Q6 Number `multiply`

Same pattern as Q1, multiplication. The PPT wants you to **recognise the pattern**, not only addition.

**File:** `src/partA/q06/Q06_NumberMultiply.java`

**Viva line:** “Overloading is resolved at compile time using the method signature.”

---

# Part 2 — Constructor overloading

**Slide idea:** a constructor runs at `new ClassName(...)`.  
Name = class name. **No return type**, not even `void`.

Several constructors = several ways to **create** the object.

If you write **any** constructor, Java does **not** invent a no-arg `ClassName()` for you.

`this.field = parameter` — `this` is the object being born.

### Q7 Student / Q8 Employee

Three levels of data:

| Student | Employee |
| --- | --- |
| name | id |
| name + roll | id + name |
| name + roll + marks | id + name + salary |

Missing pieces get placeholders (`0`, `"Not assigned"`).

**Files:** `src/partB/q07/...`, `src/partB/q08/...`

### Q9 Rectangle

- `Rectangle()` → default 1×1  
- `Rectangle(side)` → square (both sides equal)  
- `Rectangle(l, b)` → normal rectangle  

Area = `length * breadth`. A square **is a** rectangle with equal sides, so one number is enough.

**File:** `src/partB/q09/Q09_RectangleConstructors.java`

### Q10 Box

Empty box (volume 0), cube (one side used three times), or l×w×h.  
Volume = `l * w * h`. Cube 4 → 64.

**File:** `src/partB/q10/Q10_BoxConstructors.java`

### Q11 Book

Blank card / title only / full catalogue (title, author, price). Same idea as filling a library form.

**File:** `src/partB/q11/Q11_BookConstructors.java`

### Q12 Constructor chaining — `this()` (important slide)

`this(...)` means: call **another constructor of the same class**. It **must be the first statement**.

Chain in the program:

```
Student()
  → Student("Unknown")
       → Student("Unknown", 0)
            → Student("Unknown", 0, 0)   ← only here are fields assigned
```

**Print order looks backwards:** the innermost constructor finishes first, then each caller prints “Finished.” So for `new Student()` you see the **full** constructor message first.

`this()` = same class. `super()` = parent class. Do not mix them on the same first line; only one can be first.

**File:** `src/partB/q12/Q12_StudentChaining.java`

---

# Part 3 — Object as a parameter

**Slide idea:** a method can take a **whole object**, not only `int` / `double`.

```
s1.compare(s2);
```

| In the call | Inside the method |
| --- | --- |
| `s1` (before the dot) | `this` |
| `s2` (in the brackets) | the parameter |

Two styles the PPT uses:

1. **Return a new object** — originals stay the same (`Distance`, `Time`, `Complex`).
2. **Change `this`** — current object grows (`Number.add`).

### Q13 / Q14 Compare

Student marks, Employee salary. Compare `this.x` with `other.x`. Print who is higher, or a tie.

### Q15 Distance — carry 12 inches = 1 foot

```
5 ft 8 in + 3 ft 10 in
= 8 ft 18 in
= 9 ft 6 in
```

`add` returns `new Distance(...)`. `d1` and `d2` do not change.

**File:** `src/partC/q15/Q15_DistanceAdd.java`

### Q16 Number — mutate this

`this.value = this.value + n.value`  
n1: 10 → 17. n2 stays 7.

### Q17 Time — carry 60 minutes = 1 hour

`2:50 + 1:20 = 4:10`. Same carry idea as inches.

### Q18 Complex

`(3 + 4i) + (1 + 2i) = 4 + 6i`. Add real parts, add imaginary parts.

**Viva line:** “`this` is the caller; the parameter is the other object.”

---

# Part 4 — Combined questions (mix of the three skills)

The PPT’s last block of Assignment 1 sticks the skills together so you cannot treat them as separate chapters.

| Q | Mix | What happens |
| --- | --- | --- |
| 19 Box | constructors + compare object | compare volumes |
| 20 Calculator | overload `add` **and** two Number objects | Java still picks by **types you pass** |
| 21 BankAccount | constructors + `transfer(other, amount)` | money leaves `this`, enters `b`; fail if poor balance |
| 22 Product | constructors + compare price | |
| 23 Rectangle | constructors + compare area | |
| 24 Library book | **all three** | `this()` constructors, overloaded `issue()`, `receiveFrom(other)` moves copies |

Q21 in numbers: Arjun 2000, Diya 500, transfer 700 → 1300 and 1200. Transfer 5000 fails.

Q24 story: register books, issue to Guest / Aisha / Rohan, merge leftover stock from another `LibraryBook`.

---

# Part 5 — Inheritance (second PDF)

**Slide idea:** `class Child extends Parent`. Child **IS-A** parent. Child gets fields and methods. Child constructor should call `super(...)` **first** so the parent part is filled.

Three pictures:

```
Single:        Employee
                  └── Manager

Hierarchical:  Vehicle
               ├── Car
               └── Bike

Multilevel:    Person
                  └── Student
                        └── Result
```

### Q1 Employee → Manager (single)

Parent: name, salary, `displayEmployee()`.  
Child: department, `displayManager()`.  
One Manager object can show **both** layers (Kavya, 75000, Sales).

### Q2 Vehicle → Car, Bike (hierarchical)

Shared: brand, speed. Car adds doors. Bike adds engine cc.

### Q3 BankAccount → Savings / Current

Shared: deposit, withdraw, display balance.

- **Savings extra:** `addInterest()` (5% of 10000 = 500).
- **Current extra:** overdraft. Withdraw 2500 from 2000 with limit 1000 → balance **−500**. Next withdraw fails.

Current **overrides** `withdraw` because the rule changed. Savings keeps the parent withdraw.

### Q4 Person → Student → Result (multilevel)

One `Result` object has name, age, roll, course, **and** three marks.  
80 + 75 + 90 = 245, percentage `245 / 3.0`.

Constructor cascade: Result → Student → Person, each `super(...)`.

### Q5 Shape → Circle, Rectangle, Triangle

Parent only `display()`. Each child has its own data and `area()`. Hierarchical again.

---

# Part 6 — Method overriding (runtime polymorphism)

**Slide idea:** child writes a method with the **same name, same parameters, same return type** as the parent, to **replace** the parent’s behaviour.

`@Override` is optional but useful: the compiler checks you really overrode something.

| | Overloading | Overriding |
| --- | --- | --- |
| Where | same class | parent / child |
| Parameters | must **differ** | must **match** |
| Clock | compile time | **runtime** (instance methods) |

**Dynamic method dispatch** (the money slide):

```
Bank b = new SBI();
b.getRateOfInterest();   // 6.5  — SBI’s method
```

Left side = reference type (`Bank`) — what calls are **allowed**.  
Right side = object type (`SBI`) — which **instance** method body **runs**.

### Q6 Animals

`sound()` → Woof / Meow / Moo.

### Q7 Banks

SBI 6.5, HDFC 7.2, ICICI 7.0. Same `Bank b`, three objects.

### Q8 Salaries

FullTime monthly; PartTime hours×rate (160×100=16000); Contract fixed.

### Q9 Payments

Credit card / UPI / Net banking. `Payment p; p.makePayment();`

### Q10 Transport

Bus 5/km, Train 3/km, Taxi 20 + 12/km. For 10 km: 50, 30, 140.

**Viva line:** “For instance methods, Java follows the object, not the reference.”

---

# Part 7 — Method hiding (static) — the exam trap of the whole PPT

**Slide idea:** a child declares a **static** method with the same signature as a parent static method.

It **looks** like overriding. It is **not**.

| | Overriding | Hiding |
| --- | --- | --- |
| Kind | instance (`void work()`) | static (`static void show()`) |
| Follow | **object** (`new Child()`) | **reference** (`Parent p`) |
| Clock | runtime | compile time |
| `Parent p = new Child(); p.m();` | Child | **Parent** |

Memory hook:

- no `static` → follow the **object** (right of `new`)
- `static` → follow the **left side** (the variable’s type)

### Q11

```
Parent p = new Child();
Child  c = new Child();
p.show();  // Parent static show
c.show();  // Child static show
```

The `new Child()` on `p` is **ignored** for static methods.

### Q12 — both in one pair of classes (best slide to memorise)

`display()` instance → both `p` and `c` print **Child**.  
`show()` static → `p` prints **Parent**, `c` prints **Child**.

### Q13 three levels

Every object is `new Child()`, but:

- `GrandParent g` → GrandParent show  
- `Parent p` → Parent show  
- `Child c` → Child show  

Hiding is **not** “use the most specific class.” It is “use the type of the variable.”

### Q14 Employee / Manager

`work()` instance → Manager through **both** `e` and `m`.  
`companyPolicy()` static → Employee through `e`, Manager through `m`.

### Q15 shopping (five exam answers)

`Product p = new Mobile(...);` and `Mobile m = new Mobile(...);`

1. **Overriding** = `displayDetails()` (both print Mobile details)  
2. **Hiding** = `category()` (`p` = General Product, `m` = Mobile Phones)  
3. Output differs because instance uses the **object**, static uses the **reference**  
4. **Compile time** = which static `category()` (from the reference type)  
5. **Runtime** = which `displayDetails()` body (from the Mobile object)

---

# One-page cheat sheet (last slide)

1. **Overloading** — same name, different parameters, **same class**, compiler.
2. **Constructor overloading** — different `new ClassName(...)`. `this()` chains same class; `super()` fills parent.
3. **Object as parameter** — `this` vs argument. New object, or mutate this?
4. **Inheritance** — `extends`, IS-A, single / hierarchical / multilevel.
5. **Override** = instance, follow the **object**. **Hide** = static, follow the **reference**.

If a question shows `Parent p = new Child();` ask one thing: **is the method static?**  
That one question splits overriding (Q6–Q10) from hiding (Q11–Q15).

---

# How to run the matching programs

```bash
bash scripts/run-all.sh
```

Or one file:

```bash
javac -d out src/inheritance/partC/q12/Q12_OverrideVsHide.java
java -cp out inheritance.partC.q12.Q12_OverrideVsHide
```

Question-to-file map: `README.md` and `SOLUTION.md`.
