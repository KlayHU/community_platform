package com.klay.community.exception;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/10 21:43
 **/
public enum CustomizeErrorCodeException implements CustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在，要不要换个问题试试？");

    private String message;

    CustomizeErrorCodeException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
