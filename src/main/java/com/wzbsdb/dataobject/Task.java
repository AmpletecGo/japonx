package com.wzbsdb.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br>
 *  任务log表
 *
 * @author Surging
 * @create 2018/10/15
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "jp_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //数据库自增
    private Integer id;

    // 开始番号
    private String start;

    // 结束番号
    private String stop;

    // 数量
    private Integer count;

    // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    // 结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime stopTime;
}
