# Part C — Object as a Parameter

A method can take a **whole object**, not only `int` / `double` / `String`.

```java
s1.compare(s2);
```

- `s1` is the object **before the dot** → inside the method it is `this`
- `s2` is the object **in the brackets** → inside the method it is the parameter

Two styles you will see:

1. **Return a new object** (Q15 Distance, Q17 Time, Q18 Complex)  
   Originals stay the same. `Distance sum = d1.add(d2);`
2. **Change the current object** (Q16 Number)  
   `this.value` grows. The parameter object is left alone.

How to run:

```bash
javac -d out src/partC/q13/Q13_StudentCompare.java
java -cp out partC.q13.Q13_StudentCompare
```

---

## Q13 Student `compare(Student s)`

**Ask:** compare marks of two students.

**Steps**

1. Fields: roll, marks.
2. Create `s1` and `s2`.
3. Write `void compare(Student s)`.
4. Use `this.marks` vs `s.marks` and print who is higher (or tie).

---

## Q14 Employee `compareSalary(Employee e)`

Same pattern as Q13 with `salary` and names.

---

## Q15 Distance `add(Distance d)` returns a new Distance

**Ask:** add feet and inches.

**Steps**

1. Add feet, add inches.
2. If inches >= 12, convert: extra feet = inches / 12, leftover = inches % 12.
3. `return new Distance(totalFeet, totalInches);`

---

## Q16 Number `add(Number n)` changes the current object

**Ask:** add the other Number’s value into this one.

```
this.value = this.value + n.value;
```

`n1` changes; `n2` does not.

---

## Q17 Time `add(Time t)`

Hours and minutes. 60 minutes become 1 hour (same trick as inches/feet).

---

## Q18 Complex `add(Complex c)`

A complex number is `real + imag i`.

Add reals, add imaginaries: `(3+4i) + (1+2i) = 4+6i`.
