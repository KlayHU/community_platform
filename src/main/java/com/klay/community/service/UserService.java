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
        //sql字段拼接
        userExample.createCriteria().andAccountIdEqualTo(user.getAccount_id());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 0){
            //插入
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modify(user.getGmt_create());
            userMapper.insert(user);
        }else{
            //更新
            User dbUser = users.get(0);
            User userUpdate = new User();
            userUpdate.setGmt_modify(System.currentTimeMillis());
            userUpdate.setAvatar_url(user.getAvatar_url());
            userUpdate.setName(user.getName());
            userUpdate.setToken(user.getToken());
            UserExample updateExample = new UserExample();
            //sql字段拼接
            updateExample.createCriteria().andIdEqualTo(userUpdate.getId());
            userMapper.updateByExampleSelective(userUpdate, updateExample);
        }
    }
}
