package zxl.com.iotest.original;

import org.junit.Test;

import java.io.*;

public class ByteStreamTest {
    @Test
    public void inputTest() throws Exception{
        File file = new File("E:/zxl.txt");
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        is.read(bytes);
        is.close();
        System.out.println(new String(bytes));
    }

    @Test
    public void outputTest() throws  Exception{
        File file = new File("E:/zqh.txt");
        OutputStream os = new FileOutputStream(file,true);
        String a = "\r\n123123123123";
        byte[] b = a.getBytes();
        os.write(b);
        os.close();
    }
}
