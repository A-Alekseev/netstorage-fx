package ru.gb.netstoragefx.common;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MessageEncoder extends MessageToMessageEncoder<Message> {
    static Logger log = LoggerFactory.getLogger(MessageEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, List<Object> out) throws Exception {
        log.debug("Message handled to encode: " + message);
        out.add(Unpooled.wrappedBuffer(message.getByteBuf()));
    }
}
