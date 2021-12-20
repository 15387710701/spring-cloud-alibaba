package com.ms.user;

import com.ms.commons.domain.UmsMember;
import com.ms.user.service.IUmsMember;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootTest
class UserApplicationTests {
    @Autowired
    IUmsMember umsMember;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void contextLoad1() {
        UmsMember member = new UmsMember();
        member.setEmail("lhw15387710701@163.com");
        rocketMQTemplate.convertAndSend("springboot-test-123", member);
    }

    @Test
    void contextLoad() {
        UmsMember member = new UmsMember();
        member.setEmail("lhw15387710701@163.com");
        rocketMQTemplate.convertAndSend("springboot-test-123", member);
    }

    @Test
    void contextLoads() {
        //同步消息  producer向 broker 发送消息，执行 API 时同步等待， 直到broker 服务器返回发送结果 。
        UmsMember member = new UmsMember();
        SendResult sendResult = rocketMQTemplate.syncSend("springboot-test", "同步消息");
        System.out.println("消息状态：" + sendResult.getSendStatus());
        System.out.println("消息id：" + sendResult.getMsgId());
        System.out.println("消息queue：" + sendResult.getMessageQueue());
        System.out.println("消息offset：" + sendResult.getQueueOffset());
        System.out.println(sendResult);
    }

    @Test
    void contextLoads2() {
        //发送异步消息
        UmsMember member = new UmsMember();
        member.setEmail("邮箱");
        rocketMQTemplate.asyncSend("springboot-test", "啊啊", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息状态：" + sendResult.getSendStatus());
                System.out.println("消息id：" + sendResult.getMsgId());
                System.out.println("消息queue：" + sendResult.getMessageQueue());
                System.out.println("消息offset：" + sendResult.getQueueOffset());
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable e) {
                System.out.println("异常");
                System.out.println(e.getMessage());
            }
        });


    }

    @Test
    void contextLoads4() throws Exception {
        //设置重试机制  3次
        DefaultMQProducer producer = rocketMQTemplate.getProducer();
        producer.setRetryTimesWhenSendFailed(3);
        Message message = new Message("topic1", "tag1", "哈哈哈".getBytes());
        //超时时间
        SendResult sendResult = producer.send(message, 1000);
        System.out.println("消息状态：" + sendResult.getSendStatus());
        System.out.println("消息id：" + sendResult.getMsgId());
        System.out.println("消息queue：" + sendResult.getMessageQueue());
        System.out.println("消息offset：" + sendResult.getQueueOffset());
        System.out.println(sendResult);
    }

    @Test
    void contextLoads1() throws Exception {
        //设置重试机制  3次
        DefaultMQProducer producer = rocketMQTemplate.getProducer();
        producer.setRetryTimesWhenSendFailed(3);
        Message message = new Message("topic1", "tag1", "哈哈哈".getBytes());
        //超时时间
        SendResult sendResult = producer.send(message, 1000);
        System.out.println("消息状态：" + sendResult.getSendStatus());
        System.out.println("消息id：" + sendResult.getMsgId());
        System.out.println("消息queue：" + sendResult.getMessageQueue());
        System.out.println("消息offset：" + sendResult.getQueueOffset());
        System.out.println(sendResult);
    }

    /**
     * 事务消息
     *
     * @throws Exception
     */
    @Test
    void contextLoads5() throws Exception {
        //设置重试机制  3次
        org.springframework.messaging.Message<String> message = MessageBuilder.withPayload("事务消息").build();

        TransactionSendResult transaction = rocketMQTemplate.
                sendMessageInTransaction
                        ("transaction-group", "transaction-topic", message, null);
        LocalTransactionState state = transaction.getLocalTransactionState();
        System.out.println(transaction);
    }
}
