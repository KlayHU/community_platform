package com.klay.community.controller;

import com.klay.community.dto.CommentCreateDTO;
import com.klay.community.dto.ResultDTO;
import com.klay.community.exception.CustomizeErrorCodeException;
import com.klay.community.mapper.CommentMapper;
import com.klay.community.model.Comment;
import com.klay.community.model.User;
import com.klay.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/11 22:20
 **/
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                          HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCodeException.NO_LOGIN);
        }
        if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCodeException.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModify(System.currentTimeMillis());
        comment.setCommentAuthor(1L);
        comment.setFollowCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
