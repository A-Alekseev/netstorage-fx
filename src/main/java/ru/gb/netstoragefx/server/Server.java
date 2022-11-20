package ru.gb.netstoragefx.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.netstoragefx.common.Message;
import ru.gb.netstoragefx.common.MessageDecoder;
import ru.gb.netstoragefx.common.MessageEncoder;
import ru.gb.netstoragefx.common.MessageHandler;

import java.nio.charset.Charset;

public class Server {

    private static Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        EventLoopGroup auth = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            ChannelFuture future = bootstrap.group(auth, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) {
                            channel.pipeline().addLast(
                                    new MessageDecoder(),
                                    new MessageEncoder(),
                                    new MessageHandler(m-> helloProcessor(m)) //TODO
                            );
                        }
                    }).bind(8989).sync();
            log.debug("Server started.");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("e=", e);
        } finally {
            auth.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    static Message helloProcessor(Message incomingMessage){
        String answer = "Hello " + incomingMessage.getByteBuf().toString(Charset.defaultCharset());
        ByteBuf byteBufHello = Unpooled.wrappedBuffer(answer.getBytes(Charset.defaultCharset()));
        return new Message(byteBufHello);
    }
}
