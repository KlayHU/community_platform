package com.klay.community.exception;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/10 21:43
 **/
public enum CustomizeErrorCodeException implements CustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不存在，要不要换个问题试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试!"),
    SYSTEM_ERROR(2004,"忙不过来啦，请稍后再试..."),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在!"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在，要不要换个问题试试？"),
    CONTENT_IS_EMPTY(2007,"评论内容为空!")
    ;

    private Integer code;
    private String message;

    CustomizeErrorCodeException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
