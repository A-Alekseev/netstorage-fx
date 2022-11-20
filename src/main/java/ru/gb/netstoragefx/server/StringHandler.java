package ru.gb.netstoragefx.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringHandler extends SimpleChannelInboundHandler<String> {

    static int counter;

    public StringHandler(){
        ++counter;
    }

    static Logger log = LoggerFactory.getLogger(StringHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        log.debug("Client {} connected.", counter);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        log.debug("Client disconnected.");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        log.debug("Received {}", s);
        ctx.writeAndFlush("Hello "+ s);
    }
}
