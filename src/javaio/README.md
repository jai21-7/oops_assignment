# Java I/O (lectures 32–33)

`Java_IO_Lectures_32_33.pptx` was not in this workspace (Windows Downloads path only).
These programs cover the usual lecture 32–33 topics: FileReader/Writer,
BufferedReader/Writer, byte streams, append, try-with-resources, serialization.

## Two families

| | Characters (text) | Bytes (any file) |
| --- | --- | --- |
| Read | `Reader` / `FileReader` | `InputStream` / `FileInputStream` |
| Write | `Writer` / `FileWriter` | `OutputStream` / `FileOutputStream` |
| Extra | `BufferedReader.readLine()` | buffer with `byte[]` |

`read()` on a Reader returns **int**: the character, or **-1** at EOF.  
`readLine()` returns **null** at EOF.

Always `close()` streams, or use try-with-resources.

Demo files are written under `out/io-demo/` (ignored with other build output).

```bash
javac -d out src/javaio/q01/Q01_FileWriterDemo.java
java -cp out javaio.q01.Q01_FileWriterDemo
```
