package ru.gb.netstoragefx.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public class MessageDecoder extends ChannelInboundHandlerAdapter {

    static Logger log = LoggerFactory.getLogger(MessageDecoder.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf)msg;

        String str = byteBuf.toString(Charset.defaultCharset());
        log.debug("ByteBuf handled to decode: " + str);

        ctx.fireChannelRead(new Message(byteBuf));
    }
}
