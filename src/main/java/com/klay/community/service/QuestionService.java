package com.klay.community.service;

import com.klay.community.dto.PaginationDTO;
import com.klay.community.dto.QuestionDTO;
import com.klay.community.exception.CustomizeErrorCodeException;
import com.klay.community.exception.CustomizeException;
import com.klay.community.mapper.QuestionMapper;
import com.klay.community.mapper.UserMapper;
import com.klay.community.model.Question;
import com.klay.community.model.QuestionExample;
import com.klay.community.model.User;
import org.apache.ibatis.session.RowBounds;
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

        Integer currentPage =(int)questionMapper.countByExample(new QuestionExample());     //从列数拿到总数

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
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(pages,limit));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
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

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer currentPage =(int)questionMapper.countByExample(questionExample);     //从列数拿到总数
        paginationDTO. setPagination(currentPage,page,limit);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getCurrentPage()) {
            page = paginationDTO.getCurrentPage();
        }
        //分页

        Integer pages = (page - 1) * limit;

        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(pages,limit));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);     //对象快速拷贝
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCodeException.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrupdate(Question question) {
        if(question.getId() == null){
            //创建问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            //提交编辑问题
            Question questionUpdate = new Question();
            questionUpdate.setGmtModify(System.currentTimeMillis());
            questionUpdate.setTitle(question.getTitle());
            questionUpdate.setDescription(question.getDescription());
            questionUpdate.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int i = questionMapper.updateByExampleSelective(questionUpdate, example);
            if(i != 1){
                throw new CustomizeException(CustomizeErrorCodeException.QUESTION_NOT_FOUND);
            }
        }
    }
}
