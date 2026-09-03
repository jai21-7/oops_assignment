# OOPS Assignments

Java programs with beginner comments for:

1. `Overloading_Object_Parameter (1).pdf` — overloading and object as parameter
2. `Inheritance_Overriding_Method_Hiding.pdf` — inheritance, overriding, method hiding

## How to learn

1. Open the README inside the part you are studying (`src/partA/README.md` …).
2. Open the matching `.java` file. Read the top comment first, then `main`.
3. Compile and run that one program. Watch which method/constructor is printed.

## How to run one question

```bash
javac -d out src/partA/q01/Q01_Calculator.java
java -cp out partA.q01.Q01_Calculator
```

## How to run everything

```bash
bash scripts/run-all.sh
```

## Map of questions

### A. Method overloading

| Q | Program | Idea |
| --- | --- | --- |
| 1 | `partA.q01.Q01_Calculator` | `add` 2 ints / 3 ints / 2 doubles |
| 2 | `partA.q02.Q02_Area` | square, rectangle, circle |
| 3 | `partA.q03.Q03_Maximum` | `max` of 2 ints, 3 ints, 2 doubles |
| 4 | `partA.q04.Q04_StudentDisplay` | display name / marks / grade |
| 5 | `partA.q05.Q05_Printer` | print int, double, char, String |
| 6 | `partA.q06.Q06_NumberMultiply` | `multiply` overloads |

### B. Constructor overloading

| Q | Program | Idea |
| --- | --- | --- |
| 7 | `partB.q07.Q07_StudentConstructors` | name / roll / marks |
| 8 | `partB.q08.Q08_EmployeeConstructors` | id / name / salary |
| 9 | `partB.q09.Q09_RectangleConstructors` | default, square, rectangle |
| 10 | `partB.q10.Q10_BoxConstructors` | volume of box / cube |
| 11 | `partB.q11.Q11_BookConstructors` | blank, title, full book |
| 12 | `partB.q12.Q12_StudentChaining` | `this()` constructor chaining |

### C. Object as a parameter

| Q | Program | Idea |
| --- | --- | --- |
| 13 | `partC.q13.Q13_StudentCompare` | compare marks |
| 14 | `partC.q14.Q14_EmployeeCompareSalary` | compare salary |
| 15 | `partC.q15.Q15_DistanceAdd` | add feet & inches, return new object |
| 16 | `partC.q16.Q16_NumberAdd` | add into current object |
| 17 | `partC.q17.Q17_TimeAdd` | add hours & minutes |
| 18 | `partC.q18.Q18_ComplexAdd` | add complex numbers |

### D. Combined

| Q | Program | Idea |
| --- | --- | --- |
| 19 | `partD.q19.Q19_BoxCompare` | Box constructors + compare volume |
| 20 | `partD.q20.Q20_CalculatorNumbers` | `add` ints, doubles, Number objects |
| 21 | `partD.q21.Q21_BankAccountTransfer` | transfer money between accounts |
| 22 | `partD.q22.Q22_ProductComparePrice` | product constructors + compare price |
| 23 | `partD.q23.Q23_RectangleCompareArea` | rectangle constructors + compare area |
| 24 | `partD.q24.Q24_LibraryBookExample` | library book: all three skills |

## Three rules to remember

1. **Method overloading** — same name, different parameter list. Return type alone does not count.
2. **Constructor overloading** — several `new ClassName(...)` shapes. Name equals class name. No return type.
3. **Object as parameter** — `s1.compare(s2)`: `s1` is `this`, `s2` is the parameter.

---

## Assignment 2 — Inheritance, Overriding, Method Hiding

Details: `src/inheritance/partA/README.md`, `partB`, `partC`.

### A. Inheritance

| Q | Program | Idea |
| --- | --- | --- |
| 1 | `inheritance.partA.q01.Q01_EmployeeManager` | Employee → Manager |
| 2 | `inheritance.partA.q02.Q02_VehicleCarBike` | Vehicle → Car, Bike |
| 3 | `inheritance.partA.q03.Q03_BankAccounts` | savings interest + current overdraft |
| 4 | `inheritance.partA.q04.Q04_PersonStudentResult` | Person → Student → Result |
| 5 | `inheritance.partA.q05.Q05_Shapes` | Circle, Rectangle, Triangle area |

### B. Method overriding

| Q | Program | Idea |
| --- | --- | --- |
| 6 | `inheritance.partB.q06.Q06_AnimalSounds` | Dog / Cat / Cow `sound()` |
| 7 | `inheritance.partB.q07.Q07_BankInterest` | runtime polymorphism on interest |
| 8 | `inheritance.partB.q08.Q08_EmployeeSalary` | full / part / contract salary |
| 9 | `inheritance.partB.q09.Q09_Payments` | credit / UPI / net banking |
| 10 | `inheritance.partB.q10.Q10_TransportFare` | bus / train / taxi fare |

### C. Method hiding (static)

| Q | Program | Idea |
| --- | --- | --- |
| 11 | `inheritance.partC.q11.Q11_StaticHiding` | static `show()` follows the reference |
| 12 | `inheritance.partC.q12.Q12_OverrideVsHide` | instance vs static side by side |
| 13 | `inheritance.partC.q13.Q13_ThreeLevelHiding` | GrandParent → Parent → Child |
| 14 | `inheritance.partC.q14.Q14_EmployeeManagerHide` | `work()` vs `companyPolicy()` |
| 15 | `inheritance.partC.q15.Q15_ShoppingSystem` | Product → Mobile, 5 exam answers |

**Override** = instance method, chosen by the **object**.  
**Hide** = static method, chosen by the **reference type**.
