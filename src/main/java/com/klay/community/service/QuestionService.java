package com.klay.community.service;

import com.klay.community.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer limit) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer currentPage = questionMapper.count();     //从列数拿到总数
        paginationDTO. setPagination(currentPage,page,limit);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getCurrentPage()) {
            page = paginationDTO.getCurrentPage();
        }
        //分页
        Integer pages = (page - 1) * limit;
        if(pages<0){
            pages=0;
        }
        List<Question> questions = questionMapper.list(pages, limit);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);     //对象快速拷贝
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer limit) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer currentPage = questionMapper.countByUserId(userId);     //从列数拿到总数
        paginationDTO. setPagination(currentPage,page,limit);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getCurrentPage()) {
            page = paginationDTO.getCurrentPage();
        }
        //分页

        Integer pages = (page - 1) * limit;

        List<Question> questions = questionMapper.listByUserId(userId,pages, limit);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);     //对象快速拷贝
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.getQuestionById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
