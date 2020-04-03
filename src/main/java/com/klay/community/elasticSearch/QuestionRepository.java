package com.klay.community.elasticSearch;


import com.klay.community.model.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description: Long为实体类主键类型
 * @author: KlayHu
 * @create: 2020/4/2 2:19
 **/

public interface QuestionRepository extends ElasticsearchRepository<Question,Long> {
}
