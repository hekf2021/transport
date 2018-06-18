package com.xiaoka.socket.mq.handler;

import org.apache.rocketmq.common.message.MessageExt;


public interface MessageProcessor {
    /**
     * 处理消息的接口
     * @param messageExt
     * @return
     */
    public boolean handleMessage(MessageExt messageExt);
}