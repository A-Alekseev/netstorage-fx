package ru.gb.netstoragefx.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ru.gb.netstoragefx.common.Message;

public class ClientMessageHandler extends SimpleChannelInboundHandler<Message> {
    public ClientMessageHandler(OnMessageReceived callback) {
        this.callback = callback;
    }

    OnMessageReceived callback;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
callback.onReceive(message);
    }
}
