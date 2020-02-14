package com.klay.community.controller;

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
public class FirstController {

    @GetMapping("/First")
    public String First (@RequestParam(name = "name") String name , Model model){
        model.addAttribute("name",name);
        return "First";
    }
}
