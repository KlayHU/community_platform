package com.klay.community.schedule;

import com.klay.community.cache.HotTagCache;
import com.klay.community.mapper.QuestionMapper;
import com.klay.community.model.Question;
import com.klay.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/20 12:24
 **/
@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 6)
    //每天凌晨一点进行话题更新
    //@Scheduled(cron = "0 0 1 * * *")
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 5;
        log.info("HotTagsStart time : {}", new Date());
        Map<String, Integer> priorities = new HashMap<>();
        List<Question> list = new ArrayList<>();
        while (offset == 0 || list.size() == limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),
                    new RowBounds(offset, limit));

            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer value = priorities.get(tag);
                    //如果标签存在，加上计算权重的公式
                    if (value != null) {
                        priorities.put(tag, value + 5 + question.getCommentCount());
                        //如果标签不存在则不加
                    } else {
                        priorities.put(tag, 5 + question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }
        hotTagCache.updateTags(priorities);
        //log.info("HotTagsStop time : {}", new Date());
    }
}