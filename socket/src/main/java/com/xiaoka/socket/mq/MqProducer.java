package com.xiaoka.socket.mq;

import com.xiaoka.socket.constant.Constants;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MqProducer {
    private String host="192.168.1.60";
    private int port=9876;
    private static ConcurrentMap<String,DefaultMQProducer> mqProducersMap=new ConcurrentHashMap<String,DefaultMQProducer>();

    public MqProducer(String host,int port){
        this.host=host;
        this.port=port;
    }

    public void createProducer(){
        try {
            // 设置生产者组名
            DefaultMQProducer producer = new DefaultMQProducer(Constants.TRANSFER);
            // 指定nameServer的地址
            producer.setNamesrvAddr(host+":"+port);
            producer.setRetryAnotherBrokerWhenNotStoreOK(true);
            // 启动实例
            producer.start();
            mqProducersMap.putIfAbsent(Constants.TRANSFER,producer);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public DefaultMQProducer getMqProducer(String producerName){
        return mqProducersMap.get(producerName);
    }

}
