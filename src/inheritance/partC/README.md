# Part C — Method Hiding (static methods)

**Hiding** happens when a child declares a **static** method with the same signature as a parent static method.

It looks like overriding. It is not.

| | Overriding | Hiding |
| --- | --- | --- |
| Method kind | instance (`void work()`) | static (`static void show()`) |
| Chosen by | **object** (`new Child()`) | **reference** (`Parent p`) |
| When | runtime | compile time |
| `Parent p = new Child(); p.method();` | Child version | Parent version |

Memory hook:

- no `static` → follow the **object**
- `static` → follow the **left side** (the type of the variable)

```bash
javac -d out src/inheritance/partC/q11/Q11_StaticHiding.java
java -cp out inheritance.partC.q11.Q11_StaticHiding
```

---

## Q11 Parent / Child `static show()`

`Parent p = new Child(); p.show();` prints Parent.
`Child c = new Child(); c.show();` prints Child.

## Q12 instance `display()` + static `show()`

`display()` overridden (both calls print Child).
`show()` hidden (p prints Parent, c prints Child).

## Q13 GrandParent → Parent → Child

All three `show()` are static. Each reference prints **its own class**, even though every object is `new Child()`.

## Q14 Employee `work()` vs `companyPolicy()`

`work()` instance → Manager version through both `e` and `m`.
`companyPolicy()` static → Employee version through `e`, Manager version through `m`.

## Q15 Product → ElectronicProduct → Mobile

(1) Overriding = `displayDetails()`  
(2) Hiding = `category()`  
(3) Output differs because instance vs static binding  
(4) Compile time = static `category()` from the reference  
(5) Runtime = `displayDetails()` from the Mobile object  
