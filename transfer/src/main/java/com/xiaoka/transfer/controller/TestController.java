package com.xiaoka.transfer.controller;

import com.xiaoka.transfer.model.User;
import com.xiaoka.transfer.service.TestService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private DefaultMQProducer producer;

    @RequestMapping("/tt")
    public User getReports() {
        System.out.println("11111111111111111");
        return testService.findUser(1);
    }

    @RequestMapping("/gg")
    public String gg(String t) {
        try {
            System.out.println("t=="+t);
            Message message = new Message("toc_transfer", "test_tag", (t).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    System.out.println(String.format("message [%s] send success!", new String(message.getBody())));
                }
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }

}
