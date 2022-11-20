package ru.gb.netstoragefx.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.buffer.ByteBuf;
import ru.gb.netstoragefx.common.Message;

import java.nio.charset.Charset;
import java.util.List;

public class MessageDecoder extends ChannelInboundHandlerAdapter {
//public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    static Logger log = LoggerFactory.getLogger(MessageDecoder.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf)msg;


        String str = byteBuf.toString(Charset.defaultCharset());
        log.debug("ByteBuf handled to decode: " + str);

        ctx.fireChannelRead(new Message(byteBuf));
    }
}
