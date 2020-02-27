package com.klay.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/15 3:20
 **/
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}

