package com.ms.product.controller;

import com.ms.commons.utils.Result;
import com.ms.product.domain.PmsBrand;
import com.ms.product.serivice.IPmsBrand;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class RocketMQController {
    @Autowired
    private IPmsBrand brandService;
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    /**
     * 普通消息
     * @return
     */
    @GetMapping("/rocket")
    public Result result(){
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setDescript("测试消息");
        pmsBrand.setFirstLetter("啊啊");
        pmsBrand.setName("application");
        brandService.save(pmsBrand);

        SendResult sendResult = rocketMQTemplate.syncSend("TopicA:TagBrand", pmsBrand);
        log.info("发送的状态是--->{}",sendResult.getSendStatus());
        return Result.ok();

    }
}
