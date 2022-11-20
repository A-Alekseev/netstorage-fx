package ru.gb.netstoragefx.common;

@FunctionalInterface
public interface MessageProcessor {
    Message process(Message message);
}
