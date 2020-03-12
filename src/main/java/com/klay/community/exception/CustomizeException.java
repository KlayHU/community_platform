package com.klay.community.exception;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/10 20:46
 **/
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public Integer getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
    public CustomizeException(CustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
