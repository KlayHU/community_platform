package com.klay.community.mapper;

import com.klay.community.model.Comment;
import com.klay.community.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    //新增回复显示
    int incCommentCount(Comment comment);
}