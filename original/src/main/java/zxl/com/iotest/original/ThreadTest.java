package zxl.com.iotest.original;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    static File file = new File("E:/zxl.txt");
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            executorService.submit(new Test1());
        }
        executorService.shutdown();
    }

    static class Test1 implements Runnable{
        @Override
        public void run() {
            try {
                int i=0;
                OutputStream os = new FileOutputStream(file, true);
                while (i<10000){
                    i++;
                    System.out.println(Thread.currentThread().getName()+";;i="+i);
                    String a = "\r\n"+Thread.currentThread().getName()+":123123123123";
                    byte[] b = a.getBytes();
                    os.write(b);
                    os.flush();
                    Thread.sleep(10);
                }
                os.close();
                System.out.println(Thread.currentThread().getName()+";;end");
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {

            }
        }
    }
}
