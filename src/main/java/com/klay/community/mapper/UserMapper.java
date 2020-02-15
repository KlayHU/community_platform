package com.klay.community.mapper;

import com.klay.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/15 19:37
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modify) values (#{account_id},#{name},#{token},#{gmt_create},#{gmt_modify})")
    public void insert(User user);
}
