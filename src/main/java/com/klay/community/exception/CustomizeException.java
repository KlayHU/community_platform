package com.klay.community.exception;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/10 20:46
 **/
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }
    public CustomizeException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
