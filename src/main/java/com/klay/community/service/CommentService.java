package com.klay.community.service;

import com.klay.community.dto.CommentDTO;
import com.klay.community.enums.CommentTypeEnum;
import com.klay.community.enums.NotificationStatusEnum;
import com.klay.community.enums.NotificationTypeEnum;
import com.klay.community.exception.CustomizeErrorCodeException;
import com.klay.community.exception.CustomizeException;
import com.klay.community.mapper.*;
import com.klay.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/12 14:28
 **/
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
    public void insert(Comment comment, User commentator) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCodeException.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCodeException.TYPE_PARAM_WRONG);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCodeException.COMMENT_NOT_FOUND);
            }
            //回复问题
            Question dbQuestion = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if(dbQuestion==null){
                throw new CustomizeException(CustomizeErrorCodeException.QUESTION_NOT_FOUND);
            }

            commentMapper.insert(comment);
            //新增评论数
            dbComment.setCommentCount(1);
            commentExtMapper.incCommentCount(dbComment);
            //创建通知
            createNotify(comment, dbComment.getCommentAuthor(), commentator.getName(), dbQuestion.getTitle(), NotificationTypeEnum.REPLY_COMMENT, dbQuestion.getId());
        }else{
            //回复问题
            Question dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(dbQuestion==null){
                throw new CustomizeException(CustomizeErrorCodeException.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            dbQuestion.setCommentCount(1);
            questionExtMapper.incCommentCount(dbQuestion);

            //创建通知
            createNotify(comment,dbQuestion.getCreator(),commentator.getName(),dbQuestion.getTitle(),NotificationTypeEnum.REPLY_QUESTION, dbQuestion.getId());
        }
    }

    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notifyType, Long outerId) {
        if(receiver == comment.getCommentAuthor()){
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notifyType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentAuthor());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size()==0) {
            //如过没有评论，返回空集
            return new ArrayList<>();
        }
        //如果评论存在，map遍历返回评论人集合，并且把结果集转换成Set对象，去重
        Set<Long> commentAtors = comments.stream().map(comment -> comment.getCommentAuthor()).collect(Collectors.toSet());
        //拿到所有的作者id
        List<Long> userId = new ArrayList<>();
        userId.addAll(commentAtors);
        UserExample userExample = new UserExample();
        //把作者id和user表的id相匹配
        userExample.createCriteria().andIdIn(userId);
        List<User> users = userMapper.selectByExample(userExample);
        //通过map的形式循环User对象
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentAuthor()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
