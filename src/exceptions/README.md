# Exception Handling and Multithreading

From the lecture `Exception and Multi Threading.pptx` (standard Java unit).
The PPTX itself was not in this repo (only a Windows path), so these programs follow
the usual lab questions for that lecture: try/catch, throw/throws/finally,
user-defined exceptions, Thread vs Runnable, priority, sync, wait/notify, deadlock.

## Exception keywords

| Keyword | Job |
| --- | --- |
| `try` | risky code |
| `catch` | handle the error object |
| `finally` | always run (close/cleanup) |
| `throw` | throw an exception now |
| `throws` | declare that a method might throw |

Checked (must handle): `Exception` and most subclasses except `RuntimeException`.
Unchecked: `RuntimeException` family (`ArithmeticException`, `NullPointerException`...).

```bash
javac -d out src/exceptions/partA/q01/Q01_TryCatch.java
java -cp out exceptions.partA.q01.Q01_TryCatch
```

## Threads

- `start()` creates a real thread. `run()` is the work.
- `extends Thread` or `implements Runnable`.
- `synchronized` = one thread at a time on that object.
- `wait`/`notify` = threads take turns on shared data.
- Deadlock = circular waiting on locks. Fix: always lock in the same order.

Do not use deprecated `stop()`, `suspend()`, `resume()`.
