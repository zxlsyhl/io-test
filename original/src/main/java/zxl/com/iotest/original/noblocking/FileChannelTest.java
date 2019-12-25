package zxl.com.iotest.original.noblocking;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;

public class FileChannelTest {
    @Test
    public void test1() {
        try {
            File file = new File("E:\\file\\channelfile.txt");
            RandomAccessFile aFile = new RandomAccessFile(file, "rw");
            FileChannel inChannel = aFile.getChannel();
            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = inChannel.read(buf); //read into buffer.
            while (bytesRead != -1) {

                buf.flip();  //make buffer ready for read

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get()); // read 1 byte at a time
                }

                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void test2() {
        try {
            File file = new File("E:\\file\\channelfile.txt");
            File fileBak = new File("E:\\file\\channelfile_bak.txt");
            RandomAccessFile aFile = new RandomAccessFile(file, "rw");
            RandomAccessFile aFileBak = new RandomAccessFile(fileBak, "rw");
            FileChannel inChannel = aFile.getChannel();
            FileChannel inChannelBak = aFileBak.getChannel();
            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = inChannel.read(buf); //read into buffer.
            while (bytesRead != -1) {

                buf.flip();  //make buffer ready for read

//                while (buf.hasRemaining()) {
//                    System.out.print((char) buf.get()); // read 1 byte at a time
//                }
                inChannelBak.write(buf);

                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void test3(){
        try {
            File file = new File("E:\\file\\channelfile.txt");
            File fileBak = new File("E:\\file\\channelfile_bak.txt");
            RandomAccessFile aFile = new RandomAccessFile(file, "rw");
            RandomAccessFile aFileBak = new RandomAccessFile(fileBak, "rw");
            FileOutputStream fos = new FileOutputStream(file,true);
            FileChannel inChannel = fos.getChannel();

            FileChannel inChannelBak = aFileBak.getChannel();
            System.out.println(inChannel.size());
            System.out.println(inChannelBak.size());
//            inChannel.transferFrom(inChannelBak,0,inChannelBak.size());
            inChannelBak.transferTo(0,inChannelBak.size(),inChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void test4(){
        int a = SelectionKey.OP_ACCEPT;
        System.out.println(a);

        int interestSet = 1;

        boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
//        boolean isInterestedInConnect = interestSet & SelectionKey.OP_CONNECT;
//        boolean isInterestedInRead    = interestSet & SelectionKey.OP_READ;
//        boolean isInterestedInWrite   = interestSet & SelectionKey.OP_WRITE;
    }
}
