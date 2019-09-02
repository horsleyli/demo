package com.cjbs.demo.service.utils;

import lombok.Data;

/**
 * @author shj
 * @param <T>
 */
@Data
public class ApiResultUtils<T> {

    private int code = 200;

    private String msg;

    private T data;

    public ApiResultUtils() {}

    public ApiResultUtils(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResultUtils(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResultUtils(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
