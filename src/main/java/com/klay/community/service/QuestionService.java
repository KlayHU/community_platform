package com.klay.community.service;

import com.klay.community.dto.QuestionDTO;
import com.klay.community.mapper.QuestionMapper;
import com.klay.community.mapper.UserMapper;
import com.klay.community.model.Question;
import com.klay.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/27 15:54
 **/
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);     //对象快速拷贝
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }return questionDTOList;
    }
}
