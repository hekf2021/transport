package com.xiaoka.socket.mq.handler;

import com.xiaoka.socket.mq.MqConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.smartboot.socket.transport.AioSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class MessageProcessorImpl implements MessageProcessor {
    @Override
    public boolean handleMessage(MessageExt messageExt) {
        try {
            String msg = new String(messageExt.getBody());
            System.out.println("receive : " + msg);
            AioSession session = MqConsumer.getAioSession("123");
            session.write(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}