package com.klay.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/12 13:07
 **/
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
