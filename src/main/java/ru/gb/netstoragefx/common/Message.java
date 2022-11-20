package ru.gb.netstoragefx.common;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public class Message {
    private ByteBuf byteBuf;

    public Message(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    public ByteBuf getByteBuf() {
        return byteBuf;
    }

    @Override
    public String toString() {
        return "Message: " + byteBuf.toString(Charset.defaultCharset());
    }
}

