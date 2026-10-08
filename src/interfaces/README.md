# Interfaces (`Interface.pptx`)

An **interface** is a contract of method names. A class **implements** it by writing those methods.

```java
interface Playable { void play(); }
class Guitar implements Playable {
    public void play() { ... }
}
```

| | Class inheritance | Interface |
| --- | --- | --- |
| Keyword | `extends` | `implements` |
| How many | one superclass | many interfaces |
| Body | methods can have code | classic: no body (only signatures) |
| Fields | any | `public static final` constants |

`Playable p = new Guitar();` — interface type, object of the class.

A class can `extend` one class **and** `implement` interfaces (see Q5).

```bash
javac -d out src/interfaces/q01/Q01_Playable.java
java -cp out interfaces.q01.Q01_Playable
```
