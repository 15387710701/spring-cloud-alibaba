package com.ms.commons.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private Integer code;
}
