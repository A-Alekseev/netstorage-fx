package ru.gb.netstoragefx.common;

public class ByteArrayUtil {

    private byte[] append(byte[]src, byte[] app){
        byte[] result = new byte[src.length + app.length];
        for (int i = 0; i < src.length; i++) {
            result[i] = src[i];
        }
        for (int i = 0; i < app.length; i++) {
            result[src.length + i] = app[i];
        }
        return result;
    }

    private byte[] append(byte[]src, byte app){
        return append(src, new byte[]{app});
    }
}
