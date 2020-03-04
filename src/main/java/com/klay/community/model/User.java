package com.klay.community.model;

import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/15 19:40
 **/
@Data
public class User {
    private Integer id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modify;
    private String avatar_url;
}
