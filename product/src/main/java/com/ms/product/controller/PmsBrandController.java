package com.ms.product.controller;


import com.ms.commons.utils.Result;
import com.ms.product.domain.PmsBrand;
import com.ms.product.domain.UmsMember;
import com.ms.product.feign.UmsMemberFeignServer;
import com.ms.product.serivice.IPmsBrand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class PmsBrandController {
    @Autowired
    IPmsBrand pmsBrand;
    @Autowired
    UmsMemberFeignServer umsMemberFeignServer;

    @GetMapping("/getBrand")
    public Result get() {
        PmsBrand byId = pmsBrand.getById(1l);
        Result<List<UmsMember>> allMember = umsMemberFeignServer.getAllMember();
        List<UmsMember> list = allMember.getData();
        log.info("远程调用的数据--->{}", list);
        return Result.ok(byId);

    }

/*    @GetMapping("/jiaoyan")
    public Result jiaoyan(JiaoYanDTO dto)  {
        if (dto.getPassword().equals("11111")){
            throw new SellException(3000,"密码设置太简单了");
            throw new SellException(3000,"密码设置太简单了");
        }
        if (dto.getPassword().equals("111111")){
            throw new SellException(3000,"密码设置太简单了");
        }
        return Result.ok();

    }*/

  /*  @GetMapping("abc")
    public Result resultVO(String msg) {
        if (msg.equals("111")){
            throw new SellException(3000,"密码太简单");
        }
        return Result.ok();
    }*/
}
