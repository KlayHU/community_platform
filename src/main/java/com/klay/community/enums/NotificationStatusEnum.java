package com.klay.community.enums;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/16 21:03
 **/
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1);

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }

}
