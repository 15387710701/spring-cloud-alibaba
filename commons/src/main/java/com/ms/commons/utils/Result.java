package com.ms.commons.utils;

import lombok.Data;

@Data
public class Result<T> {
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAIL_CODE = 500;
    public static final Integer NO_LOGIN_CODE = 401;
    private String msg;
    private Integer code;
    private T data;

    public static <T> Result<T> ok(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static <T> Result<T> ok(String msg) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setData(null);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(FAIL_CODE);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(FAIL_CODE);
        result.setData(null);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        result.setMsg("");
        return result;
    }
}