package org.zxl.iotest.original.noblocking.randomaccessfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class LargeMappedFiles {
    static int row = 32;
    static int length = 10000*1000;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        MappedByteBuffer out = new RandomAccessFile("test.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0 , length*row);
        Random random = new Random();

        for (int i=0;i<length;i++){
            int temperture =  (int)(10+Math.random()*(99-10+1));
            System.out.println(temperture);
            int year = random.nextInt(2000)%(2000-1000+1)+1000; //生成1000到2000之间的随机数

            out.put((year+"00"+temperture+"\n").getBytes());
        }
        System.out.println("Finished writing");
    }
}
