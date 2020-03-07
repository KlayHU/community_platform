package com.klay.community.mapper;

import com.klay.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/15 19:37
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modify,avatar_url) values (#{account_id},#{name},#{token},#{gmt_create},#{gmt_modify},#{avatar_url})")
    void insert(User user);


    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{account_id}")
    User findByAccountId(@Param("account_id") String account_id);

    @Update("update user set name = #{name},token = #{token},avatar_url = #{avatar_url},gmt_modify = #{gmt_modify} where account_id = #{account_id}")
    void update(User dbUser);
}
