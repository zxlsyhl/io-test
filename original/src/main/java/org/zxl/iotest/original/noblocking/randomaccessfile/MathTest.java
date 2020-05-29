package org.zxl.iotest.original.noblocking.randomaccessfile;

import org.junit.Test;

import java.util.Random;

public class MathTest {
    @Test
    public void test1(){
        int temperture =  (int)(10+Math.random()*(100-10+1));
        System.out.println(temperture);
        Random random = new Random();
        int year = random.nextInt(2000)%(2000-1000+1)+1000;
        System.out.println(year);
    }
}
