package com.klay.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/16 15:17
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
