package com.klay.community.dto;

import com.klay.community.model.User;
import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/13 21:03
 **/
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentAuthor;
    private Long gmtCreate;
    private Long gmtModify;
    private Long followCount;
    private Integer commentCount;
    private String content;
    private User user;
}
