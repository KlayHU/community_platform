package com.klay.community.enums;

import com.klay.community.dto.ResultDTO;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/12 14:22
 **/
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if(value.getType()== type){
                return true;
            }
        }
        return false;
    }
}
