package zxl.com.iotest.mina;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;


public class DemoServer {

    private static Logger logger = Logger.getLogger(DemoServer.class);

    private static int PORT = 3025;

    public static void main(String[] args) {
        //创建非阻塞的server端的socket
        IoAcceptor acceptor = null;
        try {
            //因为程序是基于TCP/IP，所以我们添加一个SocketAcceptor,用来接收客户端的请求
            acceptor = new NioSocketAcceptor();
            // 设置日志过滤器
            //这个过滤器将会日志记录所有信息，
            // 比如 session 的新建、接收到的消息、发送的消息、session 的关闭。
            // 接下来的过滤器是一个 ProtocolCodecFilter。
            // 这个过滤器将会把二进制或者协议特定的数据翻译为消息对象，反之亦然。
            // 我们使用一个现有的 TextLine 工厂因为它将为你处理基于文本的消息
            // (你无须去编写 codec 部分)。
            acceptor.getFilterChain().addLast("logger",new LoggingFilter());
            acceptor.getFilterChain().addLast(   //添加消息过滤器
                    "codec",
                    //Mina自带的根据文本换行符编解码的TextLineCodec过滤器 看到\r\n就认为一个完整的消息结束了
                    new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                            LineDelimiter.WINDOWS.getValue(),
                            LineDelimiter.WINDOWS.getValue()
                    )));
            //设置处理线程池
            acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(1,3));
            //设置读取数据的缓冲区的大小
            acceptor.getSessionConfig().setReadBufferSize(2048);
            //读取通道10s内无操作进入空闲状态
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            //绑定逻辑处理器
            acceptor.setHandler(new Demo1ServerHandler());
            //绑定端口
            acceptor.bind(new InetSocketAddress(PORT));

            logger.info("服务器启动成功.. 端口号为:" + PORT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}