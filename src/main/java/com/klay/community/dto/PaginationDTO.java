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
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean hasPreviousPages;      //向前按钮
    private boolean hasFirstPage;       //去首页按钮
    private boolean hasNextPage;        //向后按钮
    private boolean hasEndPage;         //去末页按钮
    private Integer page;       //第 页
    private List<Integer> pages = new ArrayList<>();      //当前分页条显示的页码数
    private Integer currentPage;    //当前页码数

    public void setPagination(Integer totalCount, Integer page, Integer limit) {
        if (totalCount % limit == 0) {
            currentPage = totalCount / limit;
        } else {
            currentPage = totalCount / limit + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > currentPage) {
            page = currentPage;
        }
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= currentPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页按钮
        if (page == 1) {
            hasPreviousPages = false;
        } else {
            hasPreviousPages = true;
        }
        //是否展示下一页按钮
        if (page == currentPage) {
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
        if (pages.contains(currentPage)) {
            hasEndPage = false;
        } else {
            hasEndPage = true;
        }
    }
}
