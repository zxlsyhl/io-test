package zxl.com.iotest.netty.test1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
        //ChannelFuture cf2 = b.connect("127.0.0.1", 8764).sync();  //可以使用多个端口
        //发送消息, Buffer类型. write需要flush才发送, 可用writeFlush代替
//        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
//        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));
//        Thread.sleep(2000);
//        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
        //cf2.channel().writeAndFlush(Unpooled.copiedBuffer("999".getBytes()));

        cf1.channel().closeFuture().sync();
        //cf2.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
