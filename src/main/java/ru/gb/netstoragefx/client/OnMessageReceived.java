package ru.gb.netstoragefx.client;

import ru.gb.netstoragefx.common.Message;

@FunctionalInterface
public interface OnMessageReceived {
    void onReceive(Message message);
}
