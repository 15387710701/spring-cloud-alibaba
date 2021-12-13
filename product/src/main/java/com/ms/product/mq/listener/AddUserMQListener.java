package com.ms.product.mq.listener;

import com.ms.commons.constant.RocketMQTopicConstant;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author:SmartV
 * @date:2021/12/10 17:24
 */
@Component
@RocketMQMessageListener(topic = RocketMQTopicConstant.TOPIC_ADD_USER,consumerGroup = "my-group")
public class AddUserMQListener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        System.out.println("收到新增用户的消息");
        System.out.println(message);
    }
}
