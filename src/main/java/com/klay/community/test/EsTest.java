package com.klay.community.test;

import com.klay.community.CommunityApplication;
import com.klay.community.elasticSearch.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/4/1 16:39
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommunityApplication.class)
public class EsTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private QuestionRepository questionRepository;
    /**
     * @Description: 创建索引，es会根据Question类的@Document注解信息来创建
     * @Author: KlayHu
     * @Create: 2020/4/1 17:33
     **/

    @Test
    public void insert() {

//        Item item = new Item();
//        item.setId(1L);
//        item.setTitle("手机");
//        item.setBrand("小米");
//        item.setCategory("米6s");
//        item.setPrice(3499.00);
//        item.setImages("http://image.baidu.com/13123.jpg");
//        questionRepository.save(item);
    }
}
