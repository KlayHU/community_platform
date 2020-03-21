package com.klay.community.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/20 12:24
 **/
@Component
@Slf4j
public class HotTagTasks {
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        log.info("The time is now {}",new Date());
    }
}
