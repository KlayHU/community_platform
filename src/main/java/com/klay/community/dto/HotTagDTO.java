package com.klay.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/24 21:27
 **/
@Data
public class HotTagDTO implements Comparable{
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
