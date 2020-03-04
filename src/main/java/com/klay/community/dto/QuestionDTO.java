package com.klay.community.dto;

import com.klay.community.model.User;
import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/27 15:53
 **/
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modify;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer follow_count;
    private String tag;
    private User user;
}
