package zxl.com.iotest.original.noblocking;


/**
 * 客户端启动程序
 */
public class ClientMain {

    public static void main(String[] args) {
        int port = 8010;
        TimeClientHandler client = new TimeClientHandler("",port);
        new Thread(client,"client-001").start();
    }
}

