package com.klay.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/16 20:55
 **/
@Controller
public class PublishController {

    @GetMapping("/publish")
    public String  publish(){
        return "publish";
    }
}
