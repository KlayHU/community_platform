package com.klay.community.controller;

import com.klay.community.dto.AccessTokenDTO;
import com.klay.community.dto.GithubUser;
import com.klay.community.mapper.UserMapper;
import com.klay.community.model.User;
import com.klay.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/15 2:29
 **/
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
//      System.out.println(user.getName()+"   登录");
        if (githubUser != null) {
            User user = new User();
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();     //通过使用uuid的方式添加token
            user.setToken(token);
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modify(user.getGmt_create());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));      //写一个k-v(token)放cookie里
            //登录成功，写cookie和session
            //request.getSession().setAttribute("user", user); //把user对象放session里,即银行账户创建成功
            return "redirect:/";
        } else {
            //登录失败，返回重新登录
            return "redirect:/";
        }
    }
}