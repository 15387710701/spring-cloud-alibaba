package com.ms.product.mq.listener;

import com.ms.commons.constant.RocketMQTopicConstant;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author:SmartV
 * @date:2021/12/10 20:40
 */
@Component
@RocketMQMessageListener(topic = "springboot-test-123",consumerGroup = "my-group")
public class NoResultMessage implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        System.out.println("收到的消息:"+new String(message.getBody()));
    }
}
