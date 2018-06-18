package com.xiaoka.socket.Processor;

import com.xiaoka.socket.constant.Constants;
import com.xiaoka.socket.mq.MqConsumer;
import com.xiaoka.socket.mq.MqProducer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ServerProcessor implements MessageProcessor<String> {

    private MqProducer mqProducer;

    public ServerProcessor(MqProducer mqProducer){
        this.mqProducer=mqProducer;
    }

    @Override
    public void process(AioSession<String> session, String msg) {
        try {
            System.out.println("服务端收到："+msg);
            Message message = new Message("tos_transfer", "test_tag", (msg).getBytes(RemotingHelper.DEFAULT_CHARSET));
            DefaultMQProducer producer = mqProducer.getMqProducer(Constants.TRANSFER);

            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    System.out.println(String.format("message [%s] send success!", new String(message.getBody())));
                }
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                    try {
                        session.write("MQ服务不可用");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            MqConsumer.setClientSession("123",session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stateEvent(AioSession<String> session, StateMachineEnum stateMachineEnum, Throwable throwable) {

    }
}
