package zxl.com.iotest.original;

import org.junit.Test;

import java.io.*;

public class CharStreamTest {
    @Test
    public void inputTest() throws Exception{
        File file = new File("E:/zxl.txt");
        Reader reader = new FileReader(file);
        char[] c = new char[1024];
        reader.read(c);
        reader.close();
        System.out.println(new String(c));
    }

    @Test
    public void outputTest() throws Exception{
        File file = new File("E:/zxl.txt");
        Writer writer = new FileWriter(file,true);
        writer.write("\r\nsdfsdfsdfsd");
        writer.flush();
        writer.close();
    }
}
