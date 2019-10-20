package zxl.com.iotest.original.noblocking;

public class ServerMain {
    public static void main(String[] args) {
        int port = 8010;
        ServerSocketChannels server = new ServerSocketChannels(port);
        new Thread(server,"timeserver-001").start();
    }
}
