package ru.gb.netstoragefx.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.netstoragefx.common.Message;

public class Client {
    public static Logger log = LoggerFactory.getLogger(Client.class);
    private SocketChannel channel;
    OnMessageReceived callback;

    public Client(OnMessageReceived callback) {
        this.callback = callback;
        new Thread(() -> {
            EventLoopGroup group = new NioEventLoopGroup();

            try {
                Bootstrap bootstrap = new Bootstrap();
                ChannelFuture future = bootstrap.channel(NioSocketChannel.class)
                        .group(group)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel c) {
                                channel = c;
                                c.pipeline().addLast(
                                        new ObjectEncoder(),
                                        new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                        new ClientMessageHandler(callback)
                                );
                            }
                        }).connect("localhost", 8989);
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                log.error("e=", e);
            } finally {
                group.shutdownGracefully();
            }
        }).start();
    }

    public void sendMessage(Message message) {
        channel.writeAndFlush(message);
    }
}
