package com.klay.community.service;

import com.klay.community.mapper.UserMapper;
import com.klay.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/7 14:06
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrupdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccount_id());
        if(dbUser == null){
            //插入
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modify(user.getGmt_create());
            userMapper.insert(user);
        }else{
            //更新
            dbUser.setGmt_modify(System.currentTimeMillis());
            dbUser.setAvatar_url(user.getAvatar_url());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);

        }
    }
}
