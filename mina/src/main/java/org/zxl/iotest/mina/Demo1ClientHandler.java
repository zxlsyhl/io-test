package org.zxl.iotest.mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class Demo1ClientHandler extends IoHandlerAdapter {

    private static Logger logger = Logger.getLogger(Demo1ClientHandler.class);

    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String msg = message.toString();
        logger.info("客户端接收到的信息为：" + msg);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        logger.error("客户端发生异常...", cause);
    }

    /**
     * TODO 考虑重连的问题
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {

     /*   try {
            int failCount = 0;
            while (true) {
                Thread.sleep();
                System.out.println(((InetSocketAddress) connector.getDefaultRemoteAddress()).getAddress()
                        .getHostAddress());
                ConnectFuture future = connector.connect();
                System.out.println("断线2");
                future.awaitUninterruptibly();// 等待连接创建完成
                System.out.println("断线3");
                session = future.getSession();// 获得session
                System.out.println("断线4");
                if (session != null && session.isConnected()) {
                    System.out.println("断线5");
                    System.out.println("断线重连["
                            + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress()
                            + ":" + ((InetSocketAddress) session.getRemoteAddress()).getPort() + "]成功");
                    session.write("start");
                    break;
                } else {
                    System.out.println("断线重连失败---->" + failCount + "次");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }*/

    }
}