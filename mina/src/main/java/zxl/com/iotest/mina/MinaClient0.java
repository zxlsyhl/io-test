package zxl.com.iotest.mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
public class MinaClient0 {

    private static Logger logger = Logger.getLogger(MinaClient0.class);

    private static String HOST = "127.0.0.1";

    private static int PORT = 3025;

    public static void main(String[] args) {
        //创建一个非阻塞的客户端
        IoConnector connector = new NioSocketConnector();
        //设置链接超时时间
        connector.setConnectTimeoutMillis(30000);
        //添加过滤器
        connector.getFilterChain().addLast(   //添加消息过滤器
                "codec",
                //Mina自带的根据文本换行符编解码的TextLineCodec过滤器 看到\r\n就认为一个完整的消息结束了
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue()
                )));
        //添加业务逻辑处理器类
        connector.setHandler(new Demo1ClientHandler());
        IoSession session = null;
        try {
            ConnectFuture future = connector.connect(new InetSocketAddress(HOST, PORT));
            future.awaitUninterruptibly(); //等待连接创建完成
            session = future.getSession();
            //发送信息
            while(true){
                BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                session.write(in.readLine());
            }


            //session.write("我爱你mina");
        } catch (Exception e) {
            logger.error("客户端链接异常...", e);
        }

        session.getCloseFuture().awaitUninterruptibly();
        logger.info("我们要关闭了");
        connector.dispose();
    }
}