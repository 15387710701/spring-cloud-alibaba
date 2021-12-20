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
        //ͬ����Ϣ  producer�� broker ������Ϣ��ִ�� API ʱͬ���ȴ��� ֱ��broker ���������ط��ͽ�� ��
        UmsMember member = new UmsMember();
        SendResult sendResult = rocketMQTemplate.syncSend("springboot-test", "ͬ����Ϣ");
        System.out.println("��Ϣ״̬��" + sendResult.getSendStatus());
        System.out.println("��Ϣid��" + sendResult.getMsgId());
        System.out.println("��Ϣqueue��" + sendResult.getMessageQueue());
        System.out.println("��Ϣoffset��" + sendResult.getQueueOffset());
        System.out.println(sendResult);
    }

    @Test
    void contextLoads2() {
        //�����첽��Ϣ
        UmsMember member = new UmsMember();
        member.setEmail("����");
        rocketMQTemplate.asyncSend("springboot-test", "����", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("��Ϣ״̬��" + sendResult.getSendStatus());
                System.out.println("��Ϣid��" + sendResult.getMsgId());
                System.out.println("��Ϣqueue��" + sendResult.getMessageQueue());
                System.out.println("��Ϣoffset��" + sendResult.getQueueOffset());
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable e) {
                System.out.println("�쳣");
                System.out.println(e.getMessage());
            }
        });


    }

    @Test
    void contextLoads4() throws Exception {
        //�������Ի���  3��
        DefaultMQProducer producer = rocketMQTemplate.getProducer();
        producer.setRetryTimesWhenSendFailed(3);
        Message message = new Message("topic1", "tag1", "������".getBytes());
        //��ʱʱ��
        SendResult sendResult = producer.send(message, 1000);
        System.out.println("��Ϣ״̬��" + sendResult.getSendStatus());
        System.out.println("��Ϣid��" + sendResult.getMsgId());
        System.out.println("��Ϣqueue��" + sendResult.getMessageQueue());
        System.out.println("��Ϣoffset��" + sendResult.getQueueOffset());
        System.out.println(sendResult);
    }

    @Test
    void contextLoads1() throws Exception {
        //�������Ի���  3��
        DefaultMQProducer producer = rocketMQTemplate.getProducer();
        producer.setRetryTimesWhenSendFailed(3);
        Message message = new Message("topic1", "tag1", "������".getBytes());
        //��ʱʱ��
        SendResult sendResult = producer.send(message, 1000);
        System.out.println("��Ϣ״̬��" + sendResult.getSendStatus());
        System.out.println("��Ϣid��" + sendResult.getMsgId());
        System.out.println("��Ϣqueue��" + sendResult.getMessageQueue());
        System.out.println("��Ϣoffset��" + sendResult.getQueueOffset());
        System.out.println(sendResult);
    }

    /**
     * ������Ϣ
     *
     * @throws Exception
     */
    @Test
    void contextLoads5() throws Exception {
        //�������Ի���  3��
        org.springframework.messaging.Message<String> message = MessageBuilder.withPayload("������Ϣ").build();

        TransactionSendResult transaction = rocketMQTemplate.
                sendMessageInTransaction
                        ("transaction-group", "transaction-topic", message, null);
        LocalTransactionState state = transaction.getLocalTransactionState();
        System.out.println(transaction);
    }
}
