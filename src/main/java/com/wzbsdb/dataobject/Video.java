package com.wzbsdb.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/9/14
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "jp_video")
public class Video implements Serializable {

    private static final long serialVersionUID = 6738763469069805372L;

    //自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //数据库自动生成
    private Integer id;

    // 番号
    private String designation;

    // 影片名称
    private String name;

    // 时长
    private String runtime;

    // 演员
    private String directed;

    // 简介
    private String description;

    // 片商
    private String studio;

    // 字幕
    private String subtitleUrl;

    // 影片链接
    private String videoUrl;

    // 封面图
    private String indexUrl;

    // 预览图
    private String itemUrl;

    // 影片时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate createTime;

    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
