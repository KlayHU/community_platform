package com.klay.community.model;

import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/17 20:11
 **/
@Data
public class Question {
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
}
