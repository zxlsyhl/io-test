package zxl.com.iotest.original.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BlockSocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        while (true){
            byte[] bytes = new byte[1024];
            is.read(bytes);
            System.out.println("服务端："+new String(bytes));
            Scanner scanner = new Scanner(System.in);
            String sya = scanner.next();
            os.write(sya.getBytes());
        }
//        is.close();
//        os.close();
//        socket.close();
    }
}
