package com.klay.community.cache;

import com.klay.community.dto.HotTagDTO;
import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/24 20:47
 **/
@Component
@Data
public class HotTagCache {
    private List<String> hots = new ArrayList<>();

    public void updateTags(Map<String, Integer> tags) {
        int max = 3;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);

        tags.forEach((name, priority) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size() < 3) {
                priorityQueue.add(hotTagDTO);
            } else {
                //拿到第一个元素,即热门话题的计算出的最小权重
                HotTagDTO minHot = priorityQueue.peek();
                //如果当前标签权重比传入的标签权重大
                if (hotTagDTO.compareTo(minHot) > 0) {
                    //取出最小元素
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });
        List<String> sortTags = new ArrayList<>();
        //取出最小元素
        HotTagDTO poll = priorityQueue.poll();
        while (poll != null) {
            sortTags.add(0, poll.getName());
            poll = priorityQueue.poll();
        }
        hots = sortTags;
        System.out.println(hots);
    }
}
