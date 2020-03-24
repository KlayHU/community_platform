package com.klay.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/19 21:48
 **/
@Data
public class QuestionQueryDTO {
    private String search;
    private String tag;
    private Integer page;
    private Integer size;
}
