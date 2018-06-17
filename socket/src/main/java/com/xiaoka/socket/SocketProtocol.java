package com.xiaoka.socket;

import org.smartboot.socket.Protocol;
import org.smartboot.socket.transport.AioSession;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class SocketProtocol implements Protocol<String> {
    CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
    @Override
    public String decode(ByteBuffer readBuffer, AioSession<String> session, boolean eof) {
        String msg=null;
        try {
            msg=decoder.decode(readBuffer).toString();
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        if("".equals(msg)){
            msg = null;
        }
        return msg;
    }

    @Override
    public ByteBuffer encode(String msg, AioSession<String> session) {
        ByteBuffer buffer=null;
        try {
            buffer = ByteBuffer.wrap(msg.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
