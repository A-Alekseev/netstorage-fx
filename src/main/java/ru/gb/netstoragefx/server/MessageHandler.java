package ru.gb.netstoragefx.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.netstoragefx.common.Message;

public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    static Logger log = LoggerFactory.getLogger(MessageHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        log.debug("Message handled to process: " + message.toString());
        ctx.writeAndFlush(message);
    }
}

