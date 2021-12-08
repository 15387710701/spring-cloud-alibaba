package com.ms.user.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ms.commons.constant.RedisKey;
import com.ms.commons.domain.UmsMember;
import com.ms.commons.dto.UserLoginDTO;
import com.ms.commons.utils.GlobalParamUtil;
import com.ms.commons.utils.Result;
import com.ms.user.interceptor.LoginInterceptor;
import com.ms.user.service.IUmsMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bboy_smartv
 * @data 2021/10/11
 */
@Slf4j
@RestController
@RequestMapping("/member")
public class UmsMemberController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    IUmsMember umsMember;
    @GetMapping("/userLogin")
    public Result<UmsMember> userLogin(UserLoginDTO dto) throws Exception {
        if (StrUtil.isBlank(dto.getUsername())){
            return Result.error("账号不能为空",new UmsMember());
        }
        if (StrUtil.isBlank(dto.getPassword())){
            return Result.error("密码不能为空",new UmsMember());
        }
        if (dto.getCode()==null){
            return Result.error("验证码不能为空",new UmsMember());
        }
        UmsMember member = umsMember.getOne(new LambdaQueryWrapper<UmsMember>().eq(UmsMember::getUsername, dto.getUsername()));
        if (member==null){
            return Result.error("账号或密码错误",new UmsMember());
        }
        String md5Str = SecureUtil.md5(dto.getPassword());
        if (member.getPassword().equals(md5Str)){
            String token = RandomUtil.randomString(20);
            if (!redisTemplate.hasKey(RedisKey.USER_TOKEN+member.getId())) {
                String s = JSON.toJSONString(member);
                redisTemplate.opsForValue().set(RedisKey.USER_TOKEN+token,s);
            }
            member.setEmail(token);
            return Result.ok(member);
        }
        return Result.error("账号或密码错误",new UmsMember());
    }

    @GetMapping("/getAllMember")
    public Result<List<UmsMember>> getAllMember(){
       /* UmsMember user = GlobalParamUtil.getUser();
        log.info("user---->{}",user);*/
        return Result.ok(umsMember.list());
    }

    @PostMapping
    public Result addMember(UmsMember member){
        MD5 md5 = MD5.create();
        byte[] digest = md5.digest(member.getPassword());
        member.setPassword(new String(digest));
        umsMember.save(member);
        return Result.ok();
    }

    public static void main(String[] args) {
        String md5Str = SecureUtil.md5("123456");
        System.out.println(md5Str);
    }
}
