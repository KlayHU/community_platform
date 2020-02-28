package com.klay.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/28 20:21
 **/
@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean hasPreciousPages;      //判断是否有向前按钮
    private boolean hasFirstPage;       //判断是否有第一页
    private boolean hasNextPage;
    private boolean hasEndPage;
    private Integer currentPage;
    private Integer page;       //第 页
    private List<Integer> pages = new ArrayList<>();      //当前分页条显示的页码数

    public void setPagintion(Integer totalCount, Integer page, Integer limit) {
        Integer totalPage;      //当前页码数
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - 1 > 0) {
                pages.add(page - i, 0);
            }
            if (page + i <= totalCount) {
                pages.add(page + 1);
            }
        }
        //是否展示上一页按钮
        if (page == 1) {
            hasFirstPage = false;
        } else {
            hasFirstPage = true;
        }
        //是否展示下一页按钮
        if (page == totalPage) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
        //是否展示去往第一页的按钮
        if (pages.contains(1)) {
            hasFirstPage = false;
        } else {
            hasFirstPage = true;
        }
        //是否展示末页按钮
        if (pages.contains(totalCount)) {
            hasEndPage = false;
        } else {
            hasEndPage = true;
        }
    }
}
