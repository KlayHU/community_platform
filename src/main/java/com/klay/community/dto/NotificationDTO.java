package com.klay.community.dto;

import com.klay.community.model.User;
import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/17 15:16
 **/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
