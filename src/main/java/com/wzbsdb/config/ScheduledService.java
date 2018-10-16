package com.wzbsdb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.wzbsdb.self.JpaonxService;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/10/15
 * @since 1.0.0
 */
@Slf4j
@Component
public class ScheduledService {

    @Autowired
    private JpaonxService jpaonxService;

    /**
     * 每天00:00启动一次
     */
    @Scheduled(cron = "0 14 17 * * ?")
    public void scheduled(){
        log.info("定时任务开启");
        jpaonxService.getJpaonx();
        log.info("定时任务结束");
    }
}
