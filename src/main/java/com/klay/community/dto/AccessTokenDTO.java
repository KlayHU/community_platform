package com.klay.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/15 2:42
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
