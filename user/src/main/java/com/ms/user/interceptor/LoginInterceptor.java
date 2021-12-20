package com.ms.user.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ms.commons.constant.RedisKey;
import com.ms.commons.domain.UmsMember;
import com.ms.commons.utils.GlobalParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      /*  String token = request.getParameter("token");
        if (StrUtil.isBlank(token)){
            return false;
        }*/
        log.info("进入了preHandle");

        if (log.isInfoEnabled()) {
            String uri = request.getRequestURI();
            log.info(uri);
            String ipAddr = getIpAddr(request);
            log.info("用户访问地址: {}, 来路地址: {}", uri, getIpAddr(request));
        }
    /*    UmsMember userInfo = getUserInfo(token);
        if (userInfo!=null){
            GlobalParamUtil.setUser(userInfo);
        }*/
        return true;
    }

    private UmsMember getUserInfo(String token) {
        String s = redisTemplate.opsForValue().get(RedisKey.USER_TOKEN + token);
        UmsMember member = JSON.parseObject(s, UmsMember.class);
        return member;
    }

    /**
     * 获取真实IP
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

}
