package javaio.q04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Byte streams — FileInputStream / FileOutputStream
 * -------------------------------------------------
 * Character streams (Reader/Writer) = TEXT.
 * Byte streams (InputStream/OutputStream) = ANY file (images, pdf, class files).
 *
 * We copy a small binary-ish file byte by byte (array buffer is faster).
 */

public class Q04_ByteCopy {
    public static void main(String[] args) {
        File dir = new File("out/io-demo");
        dir.mkdirs();
        File src = new File(dir, "source.bin");
        File dst = new File(dir, "copy.bin");
        try {
            FileOutputStream make = new FileOutputStream(src);
            make.write(new byte[] { 10, 20, 30, 40, 50 });
            make.close();

            FileInputStream in = new FileInputStream(src);
            FileOutputStream out = new FileOutputStream(dst);
            byte[] buf = new byte[1024];
            int n;
            int total = 0;
            while ((n = in.read(buf)) != -1) {
                out.write(buf, 0, n);
                total += n;
            }
            in.close();
            out.close();
            System.out.println("Copied " + total + " bytes to " + dst.getPath());
        } catch (IOException e) {
            System.out.println("Copy failed: " + e.getMessage());
        }
    }
}
