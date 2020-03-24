package com.klay.community.controller;

import com.klay.community.dto.CommentDTO;
import com.klay.community.dto.QuestionDTO;
import com.klay.community.enums.CommentTypeEnum;
import com.klay.community.service.CommentService;
import com.klay.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/6 11:59
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        List<QuestionDTO> relatedQuestion = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //新增阅读
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestion",relatedQuestion);
        return "question";
    }
}
