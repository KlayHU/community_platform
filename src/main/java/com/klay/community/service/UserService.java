package com.klay.community.service;

import com.klay.community.mapper.UserMapper;
import com.klay.community.model.User;
import com.klay.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModify(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
}
