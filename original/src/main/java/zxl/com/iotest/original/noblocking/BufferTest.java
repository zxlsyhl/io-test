package zxl.com.iotest.original.noblocking;

import org.junit.Test;

import java.nio.ByteBuffer;

public class BufferTest {
    @Test
    public void test1(){
        ByteBuffer bb = ByteBuffer.allocate(5);
        byte b1 = 127;
        while (bb.position()<bb.capacity()){
            bb.put(b1);
            System.out.println("bb.put done");
            System.out.println("bb.capacity():"+bb.capacity());
            System.out.println("bb.limit():"+bb.limit());
            System.out.println("bb.position():"+bb.position());
        }
        bb.flip();
        System.out.println("step into read****************************************");
//        byte b2 = bb.get();
//        byte b3 = bb.get();
//        byte b4 = bb.get();
        while (bb.hasRemaining()) {
            System.out.println("bb.capacity():"+bb.capacity());
            System.out.println("bb.limit():"+bb.limit());
            System.out.println("bb.position():"+bb.position());
            System.out.println("bb.get():"+bb.get()); // read 1 byte at a time
        }
//        System.out.println("b2:"+b2);

    }
}
