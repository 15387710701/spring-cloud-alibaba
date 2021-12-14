package com.ms.commons.exception;

import lombok.Getter;

@Getter //通过lombok插件实现省写setter或者getter方法
public class SellException extends RuntimeException {

    private Integer code;
    private String message;

//    public SellException(ResultEnum resultEnum) {
//        super(resultEnum.getMessage());
//        this.code = resultEnum.getCode();
//    }

    public SellException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}