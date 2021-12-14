package com.ms.user.mq;

import org.apache.logging.log4j.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

public class BrandRocketMQ implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {

    }
}
