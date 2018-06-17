package com.xiaoka.transfer.mq.handler;

import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessorImpl implements MessageProcessor {
    @Override
    public boolean handleMessage(MessageExt messageExt) {
        String msg = new String(messageExt.getBody());
        System.out.println("receive : " + msg);
        return true;
    }
}