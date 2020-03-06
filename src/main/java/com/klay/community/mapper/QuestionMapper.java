package com.klay.community.mapper;

import com.klay.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/17 20:05
 **/
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modify,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modify},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{pages},#{limit}")
    List<Question> list(@Param(value = "pages") Integer pages, @Param(value = "limit") Integer limit);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{pages},#{limit}")
    List<Question> listByUserId(@Param("userId") Integer userId , @Param(value = "pages") Integer pages , @Param(value = "limit") Integer limit);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getQuestionById(@Param("id") Integer id);
}

