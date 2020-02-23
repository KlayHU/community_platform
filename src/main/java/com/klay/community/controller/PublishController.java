package com.klay.community.controller;

import com.klay.community.mapper.QuestionMapper;
import com.klay.community.mapper.UserMapper;
import com.klay.community.model.Question;
import com.klay.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/16 20:55
 **/
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model) {

        if(title == null || title == "") {
            model.addAttribute("Error", "标题不能为空");
            return "publish";
        }if(description == null || description == ""){
            model.addAttribute("Error","问题描述不能为空");
            return "publish";
        }if(tag == null || tag == ""){
            model.addAttribute("Error","标签不能为空");
            return "publish";
        }

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
            if (user == null) {
                model.addAttribute("Error", "请先登录！");
                return "publish";
            }
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modify(question.getGmt_create());

            questionMapper.create(question);
            return "redirect:/";
        }
}