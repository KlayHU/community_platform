package com.klay.community.mapper;

import com.klay.community.model.Comment;


public interface CommentExtMapper {
    //新增回复显示
    int incCommentCount(Comment comment);
}