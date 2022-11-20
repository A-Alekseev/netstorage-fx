package ru.gb.netstoragefx.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Message {

    static final Charset MessageCharset = Charset.defaultCharset();

    private String commandName;
    private String[] commandArguments;
    private ByteBuf byteBuf;
    private int fileContentPosition;

    public Message(String commandName, String[] commandArguments, byte[] fileContent) {
        //TODO
    }

    //<command name length><command name><command arguments count>< 1 arg len>< 1 arg content>< 2 arg len>< 2 arg content>... <file content>
    public Message(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
        //TODO
    }

    public ByteBuf getByteBuf() {
        return byteBuf;
    }

    @Override
    public String toString(){
        return byteBuf.toString(MessageCharset);
    }
}

