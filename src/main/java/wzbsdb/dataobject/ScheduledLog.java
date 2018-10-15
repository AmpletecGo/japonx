package wzbsdb.dataobject;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br>
 *  任务log表
 *
 * @author Surging
 * @create 2018/10/15
 * @since 1.0.0
 */
public class ScheduledLog {

    private Integer id;

    // 开始番号
    private String start;

    // 结束番号
    private String stop;

    private LocalDateTime startTime;

    private LocalDateTime stopTime;

}
