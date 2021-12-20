package com.ms.commons.exception;

import com.ms.commons.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public Result handlerSellerException(SellException e) {
        System.out.println("错误信息是:" + e.getMessage());
        return Result.error(e.getMessage(), e.getCode());
    }

}