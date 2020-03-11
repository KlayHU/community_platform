package com.klay.community.controller;

import com.klay.community.dto.QuestionDTO;
import com.klay.community.mapper.QuestionMapper;
import com.klay.community.model.Question;
import com.klay.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/6 11:59
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
