package com.klay.community.enums;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/16 17:56
 **/
public enum  NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");

    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String nameOfType(int type){
        for (NotificationTypeEnum value : NotificationTypeEnum.values()) {
            if(value.getType() == type){
                return value.getName();
            }
        }
        return "";
    }

    NotificationTypeEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }
}
