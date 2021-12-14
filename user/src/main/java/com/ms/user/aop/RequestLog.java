package com.ms.user.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ms.commons.constant.RedisKey;
import com.ms.commons.utils.Result;
import com.ms.user.annotation.IsNotFreeze;
import com.ms.user.domain.UmsMember;
import com.ms.user.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class RequestLog {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Pointcut(" !execution(* com.ms.user.controller.login*.*(..))")
    public void merchantRequestServer1() {
    }

    @Pointcut("execution(* com.ms.user.controller.*.*(..))")
    public void merchantRequestServer() {
    }

    /**
     * 注解实现状态校验
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("merchantRequestServer1() && merchantRequestServer() ")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("request{}",request);
        Object result = joinPoint.proceed();
        log.info("result-->{}",result);
        log.info("方法执行之前 环绕");

        if (!redisTemplate.hasKey(RedisKey.USER_TOKEN + "admin")){
            return Result.error("用户没登录");
        }
        String s = redisTemplate.opsForValue().get(RedisKey.USER_TOKEN + "admin");
        UmsMember admin = JSONObject.parseObject(s, UmsMember.class);

        //校验是否是允许的状态

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        IsNotFreeze annotation = method.getAnnotation(IsNotFreeze.class);
        if (!annotation.freeze()){
            int status = admin.getStatus();
            if (status==1){
                return Result.error("账户已冻结,不能访问");
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 出现异常执行此方法 出现异常直接来到此方法
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "merchantRequestServer()",
            throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e){
        String name = joinPoint.getSignature().getName();
        log.info("方法执行异常");
        log.info("异常信息{}",e.getMessage());
    }

    @After( value = "merchantRequestServer()")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("方法执行之后");
    }

    @Before( value = "merchantRequestServer()")
    public void before(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，打印请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        log.info("方法执行之前");
    }
}
