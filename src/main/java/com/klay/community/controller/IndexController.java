package com.klay.community.controller;

import com.klay.community.dto.PaginationDTO;
import com.klay.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/12 2:44
 **/
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "limit", defaultValue = "2") Integer limit) {
        PaginationDTO pagination = questionService.list(page,limit);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
