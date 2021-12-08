package com.ms.product.feign;

import com.ms.commons.utils.Result;
import com.ms.product.domain.UmsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author:SmartV
 * @date:2021/10/11 10:22
 */
@FeignClient("member")
public interface UmsMemberFeignServer {
    @GetMapping("/member/getAllMember")
    Result<List<UmsMember>> getAllMember();
}
