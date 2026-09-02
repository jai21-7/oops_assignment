# Part D — Combined questions

These programs mix the three skills:

| Skill | Meaning |
| --- | --- |
| Method overloading | Same method name, different parameters |
| Constructor overloading | Same class, different `new ClassName(...)` |
| Object as parameter | Pass another object into a method (`this` vs the parameter) |

How to run:

```bash
javac -d out src/partD/q24/Q24_LibraryBookExample.java
java -cp out partD.q24.Q24_LibraryBookExample
```

---

## Q19 Box constructors + `compare(Box b)`

Create boxes three ways (default, cube, length/width/height).
`volume = l * w * h`. Compare `this.volume()` with `b.volume()`.

---

## Q20 Calculator `add` ints, doubles, and two Number objects

`add(Number a, Number b)` uses `a.value + b.value`.
Java still picks the method by the **types** you pass.

---

## Q21 BankAccount transfer

`a1.transfer(a2, 700)` means: take 700 from `a1` (this) and give it to `a2` (b).

Steps inside `transfer`:

1. Reject amount <= 0.
2. If `this.balance < amount`, print failed.
3. Else subtract from this, add to b.

---

## Q22 Product constructors + `comparePrice`

Open a product with name only, name+price, or full details.
Then compare `this.price` with `p.price`.

---

## Q23 Rectangle constructors + `compareArea`

Same idea as Q9 + Q19, using area instead of volume.

---

## Q24 Library book — all three ideas

Real-life example: college library.

1. **Constructors** — blank book, title only, full details (`this()` chaining).
2. **Overloaded `issue`** — guest / named student / several copies.
3. **`receiveFrom(LibraryBook other)`** — move leftover copies from another object.

Read `Q24_LibraryBookExample.java` from top to bottom; `main` is labelled STEP 1, 2, 3.
