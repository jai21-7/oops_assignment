# Student Management System — learn it in order

This is a small console program you can read from the first class to the menu. Each step adds one idea and leaves the previous idea working.

The ideas, in the order the code uses them:

1. **Encapsulation** — private fields, checks in setters (`Person`, `SubjectMark`, `Attendance`)
2. **Inheritance** — `Student` is a `Person` (`extends`)
3. **Composition** — a student *has* marks and *has* attendance
4. **Collections** — `ArrayList` to list students, `HashMap` to find one by roll number
5. **File handling** — three text files so the data survives after the program closes

Suggested classes (this is the whole design):

| Class | Job |
| --- | --- |
| `Person` | name and age, with checks |
| `Student` | roll, course, marks, attendance, CGPA |
| `SubjectMark` | one subject and its marks, grade, grade point |
| `Attendance` | classes held / attended, percentage, shortage |
| `StudentRegistry` | add, update, delete, search, list |
| `StudentFileStore` | save and load the text files |
| `StudentManagementApp` | the menu |

## How to run it

From the project folder:

```bash
javac -d out src/sms/model/*.java src/sms/store/*.java src/sms/StudentManagementApp.java
java -cp out sms.StudentManagementApp
```

That opens a menu. Your data is written to `data/sms/` (`students.txt`, `marks.txt`, `attendance.txt`). That folder is not committed to git, so your local students stay on your machine.

Watch the rules run with no typing:

```bash
java -cp out sms.StudentManagementApp demo
```

The demo adds students, rejects bad data, computes CGPA, saves, loads into a second registry, and prints one report card. If a rule is broken, it stops and says which check failed.

## The picture

```
Person
  name, age
     ^
     | extends (IS-A)
     |
Student
  rollNumber, course
  has a List of SubjectMark
  has an Attendance
```

`StudentRegistry` holds many `Student` objects. `StudentFileStore` turns that list into files and back. The menu only calls those two.

---

## Step 1 — Encapsulation (`Person`)

Open `src/sms/model/Person.java`.

A person has a name and an age. Both fields are `private`. Nothing outside the class can write `person.age = -5`, because that line would not compile.

The constructor does not assign the fields itself. It calls `setName` and `setAge`. The rules live in the setters, so creating a person and editing a person cannot drift apart.

Rules in this class:

- Name is 2 to 50 characters, contains a letter, and has no `|` (the save file uses `|` as a separator).
- Age is 15 to 70.

When a rule fails, the setter throws `IllegalArgumentException`. The menu catches that and prints the message. The bad value is never stored.

Try to notice: `this.name = cleaned` means "the field of *this* object", not the parameter. The parameter is also called `name`. `this` tells them apart.

## Step 2 — Inheritance (`Student extends Person`)

Open `src/sms/model/Student.java`.

`class Student extends Person` means a student is a person plus extra data. `Student` does not declare `name` or `age` again. It inherits them.

The first line of the constructor is `super(name, age)`. That runs `Person`'s constructor, which runs the checks from Step 1. If the age is 10, `Student`'s constructor never finishes.

What a student adds:

- `rollNumber` — `final`. After `new Student(...)`, the roll cannot change. Step 4 uses it as a map key. A key that changes is a bug.
- `course` — can change, through `setCourse`.
- a list of `SubjectMark`
- one `Attendance` object

`getName()` and `setName()` are called on a `Student` even though they are written in `Person`. That is the point of inheritance: the child uses the parent's methods.

## Step 3 — Marks, CGPA, and attendance

Open `SubjectMark.java` and `Attendance.java`, then come back to the methods at the bottom of `Student.java`.

### One subject

`SubjectMark` stores the subject name and marks from 0 to 100. Grade letter and grade point are calculated, not stored:

| Marks | Letter | Point |
| --- | --- | --- |
| 90–100 | O | 10 |
| 80–89 | A+ | 9 |
| 70–79 | A | 8 |
| 60–69 | B+ | 7 |
| 50–59 | B | 6 |
| 40–49 | C | 5 |
| 0–39 | F | 0 |

### CGPA

`Student.cgpa()` adds the grade points and divides by how many subjects there are.

Example: Java 91 (point 10) and Maths 76 (point 8).

CGPA = (10 + 8) / 2 = 9.00

No subjects yet: CGPA is 0. A failed subject still counts as 0, so it lowers the average. Calling `putMark("Java", 91)` a second time replaces the old Java row. `"java"` and `"Java"` are the same subject.

### Attendance

`Attendance` stores two counts only. The percentage is computed:

percentage = attended × 100 / held

If no class has been held, the percentage is 0 (we do not divide by zero).

Shortage means at least one class was held and the percentage is under 75.

Two ways to change attendance, because both show up in real use:

- `recordClass(true/false)` — one more class, present or absent
- `setTotals(held, attended)` — replace both numbers, and reject attended > held

### Why the list is private

`getMarks()` returns `List.copyOf(marks)`. That is a copy. If the menu did `student.getMarks().clear()`, the student's real list would stay intact. Returning the real `ArrayList` would let any caller break encapsulation from the outside.

This is composition: `Student` *has* marks. Marks are not a kind of student, so `SubjectMark` does not `extend Student`.

## Step 4 — Collections (`StudentRegistry`)

Open `src/sms/store/StudentRegistry.java`.

The registry is the in-memory list of every student. Two collections hold the **same objects**:

- `ArrayList<Student>` — insertion order, used to print everyone and to search by name
- `HashMap<Integer, Student>` — roll number to student, used when you already know the roll

Search by roll is a map lookup. Search by name walks the list and keeps names that contain your text, ignoring capital letters. `"rao"` matches `"Asha R. Rao"`.

`addStudent` refuses a roll that is already in the map. `deleteStudent` removes the object from the list **and** the map. If it removed only one, the two collections would disagree.

`updateStudent` changes name, age, and course. It does not change the roll. To "change" a roll, delete that student and add a new one.

`all()` returns a copy of the list, same idea as `getMarks()`.

## Step 5 — File handling (`StudentFileStore`)

Open `src/sms/store/StudentFileStore.java`.

Memory disappears when the program exits. `save` writes three UTF-8 text files:

```
students.txt
# roll|name|age|course
101|Asha R. Rao|20|BCA

marks.txt
# roll|subject|marks
101|Java|91
101|Maths|76

attendance.txt
# roll|classesHeld|classesAttended
101|40|36
```

`load` reads students first, then marks, then attendance. A mark line whose roll is not in `students.txt` is an error. A `#` line is a comment.

The menu saves after every successful change, and again when you choose 0 to exit. Option 13 saves on purpose. Option 14 loads and **replaces** whatever is currently in memory.

`try-with-resources` (the `try (BufferedWriter writer = ...)`) closes the file even if writing throws. That is the same pattern as the Java I/O lecture on try-with-resources.

## Step 6 — The menu

Open `src/sms/StudentManagementApp.java` after the other classes make sense.

The menu does not calculate CGPA and does not know the file format. It reads a line, calls the registry, and saves.

| Choice | What it calls |
| --- | --- |
| 1 Add | `new Student` then `registry.addStudent` |
| 2 Update | `registry.updateStudent` (blank field keeps the old value) |
| 3 Delete | `registry.deleteStudent` |
| 4 Search roll | `registry.findByRoll` |
| 5 Search name | `registry.findByName` |
| 6 List | `registry.all` |
| 7 Marks | `student.putMark` |
| 8 Remove subject | `student.removeMark` |
| 9 and 12 Report | `student.reportCard` (marks, CGPA, and attendance together) |
| 10 One class | `attendance.recordClass` |
| 11 Totals | `attendance.setTotals` |
| 13 / 14 | `fileStore.save` / `fileStore.load` |

Bad input (letters where a number is required, marks 140, duplicate roll) prints a reason and returns to the menu. The program does not exit.

## A path to follow while you read

1. Read `Person` and say out loud what a caller is not allowed to store.
2. Read `Student`'s constructor and find `super`.
3. Pick marks 88 and 76 and compute the CGPA on paper. Then find the method that does that division.
4. In `StudentRegistry`, find the two lines that run for every add, and the two lines that run for every delete.
5. Run the demo. Open `out/sms-demo/students.txt` and match each field to a getter.
6. Run the menu, add yourself, exit, run the menu again, and search for your roll. That is the file round-trip.

## What this project deliberately leaves out

There is no database, no login, and no graphical window. Those hide the four ideas. Once the menu, the list, and the text files feel ordinary, a later version can swap `StudentFileStore` for something else without rewriting `Student`.
