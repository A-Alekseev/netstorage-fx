package ru.gb.netstoragefx.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    static Logger log = LoggerFactory.getLogger(MessageHandler.class);

    private MessageProcessor processor;

    public MessageHandler(MessageProcessor processor) {
        this.processor = processor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        log.debug("Message handled to process: " + message.toString());
        Message resultMessage = processor.process(message);
        ctx.writeAndFlush(resultMessage);
    }
}

