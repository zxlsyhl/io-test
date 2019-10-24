package zxl.com.iotest.original.blocking;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class ByteTest {
    @Test
    public void testByte(){

        String a = "张";
        byte[] b = a.getBytes();
        System.out.println("b.length:"+b.length);
        for (byte c :b){
            System.out.println(c);
            String mbyteToString= Integer.toBinaryString((c & 0xFF) + 0x100).substring(1);
            System.out.println("c:"+mbyteToString);
        }
        byte[] d = new byte[3];
//        d[0] = b[0];
        d[1] = b[1];
        d[2] = b[2];
        System.out.println("d:"+new String(d));

    }

    @Test
    public void test2() throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("张小龙你好吗");

        File file = new File("E:/test/ioTest.txt");
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[900];
        while (inputStream.read(bytes)>0){
            System.out.println(new String(bytes));
        }
        inputStream.close();
    }

    public void test3(){
        byte a = 1;
        HashMap s = new HashMap();
    }
}
