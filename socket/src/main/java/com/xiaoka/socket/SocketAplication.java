package com.xiaoka.socket;

import com.xiaoka.socket.Processor.ServerProcessor;
import com.xiaoka.socket.mq.MqConsumer;
import com.xiaoka.socket.mq.MqProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.smartboot.socket.transport.AioQuickServer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SocketAplication {

    public static int port=8888;
    public static int threadNum=10;
    public static int bufferSize=20480;
    public static int queueSize=10;

    private static String namesrvAddr="192.168.1.60:9876";

    public static MqProducer mqProducer;
    public static MqConsumer mqConsumer;

    public static void main(String[] args){
        try {
            mqProducer = new MqProducer(namesrvAddr);
            mqProducer.createProducer();
            mqConsumer = new MqConsumer(namesrvAddr);
            mqConsumer.createConsumer();
            AioQuickServer<String> server = new AioQuickServer<String>(port, new SocketProtocol(), new ServerProcessor(mqProducer));
            server.setBannerEnabled(true);
            server.setReadBufferSize(bufferSize);
            server.setThreadNum(threadNum);
            server.setWriteQueueSize(queueSize);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
