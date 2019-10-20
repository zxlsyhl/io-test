package zxl.com.iotest.original.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockSocketServer {
    public static void main(String[] args)  throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true){
            //等待客户端连接
            Socket socket = serverSocket.accept();
            System.out.println("有新连接接入："+socket.getPort());
            executorService.submit(new HandlerSockert(socket));
        }
//        serverSocket.close();
    }

    static class HandlerSockert implements Runnable{
        private Socket socket;

        public HandlerSockert(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                OutputStream os = socket.getOutputStream();
                InputStream is = socket.getInputStream();
                String clisay = "";
                while (!"bye".equals(clisay)) {
                    os.write("Hello world".getBytes());
                    byte[] bytes = new byte[1024];
                    is.read(bytes);
                    clisay = new String(bytes);
                    System.out.println("客户端：" + clisay);
                }
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

}
