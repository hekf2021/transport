package com.xiaoka.socket.mq;

import com.xiaoka.socket.mq.handler.MessageProcessor;
import com.xiaoka.socket.mq.handler.MessageProcessorImpl;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.smartboot.socket.transport.AioSession;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MqConsumer {
    private static ConcurrentMap<String,AioSession> clientSession= new ConcurrentHashMap<String,AioSession>();
    private static String namesrvAddr;

    public MqConsumer(String namesrvAddr){
        this.namesrvAddr=namesrvAddr;
    }

    public void createConsumer(){
        try {
            //设置消费者组名
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //指定nameServer的地址
            consumer.setNamesrvAddr(namesrvAddr);
            //指定订阅的topic及tag表达式
            consumer.subscribe("toc_transfer", "test_tag");

            MessageProcessor messageProcessor =new MessageProcessorImpl();
            MessageListener messageListener = new MessageListener();
            messageListener.setMessageProcessor(messageProcessor);
            consumer.registerMessageListener(messageListener);

            //启动消费者实例
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setClientSession(String clientId,AioSession session){
        clientSession.put(clientId,session);
    }

    public static AioSession getAioSession(String clientId){
        return clientSession.get(clientId);
    }
}
