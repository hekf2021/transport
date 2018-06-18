package com.xiaoka.socket;

import com.xiaoka.socket.Processor.ServerProcessor;
import com.xiaoka.socket.constant.Constants;
import com.xiaoka.socket.mq.MqConsumer;
import com.xiaoka.socket.mq.MqProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.smartboot.socket.transport.AioQuickServer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SocketAplication {

    private static int port;
    private static int threadNum;
    private static int bufferSize;
    private static int queueSize;
    private static String namesrvAddr;
    private static MqProducer mqProducer;
    private static MqConsumer mqConsumer;



    public static void main(String[] args){
        try {
            new Constants().initData();
            initConfig();
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

    private static void initConfig(){
        port = Constants.getIntValue(Constants.TCP_SERVER_CONFIG.PORT);
        threadNum = Constants.getIntValue(Constants.TCP_SERVER_CONFIG.THREAD_NUM);
        bufferSize = Constants.getIntValue(Constants.TCP_SERVER_CONFIG.BUFFER_SIZE);
        queueSize = Constants.getIntValue(Constants.TCP_SERVER_CONFIG.QUEUE_SIZE);
        namesrvAddr = Constants.getStringValue(Constants.TCP_SERVER_CONFIG.NAMESRV_ADDR);
    }


}
