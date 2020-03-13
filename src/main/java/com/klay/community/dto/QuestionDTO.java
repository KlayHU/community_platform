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
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModify;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer followCount;
    private String tag;
    private User user;
}
