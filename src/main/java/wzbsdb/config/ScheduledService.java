package wzbsdb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wzbsdb.self.JpaonxService;

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

    /**
     * 每天00:00启动一次
     */
    @Scheduled(cron = "0 33 17 * * ?")
    public void scheduled(){
        JpaonxService jpaonxService = new JpaonxService();
        jpaonxService.getJpaonx();
    }
}
