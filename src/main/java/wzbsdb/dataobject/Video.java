package wzbsdb.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "video")
public class Video implements Serializable {

    private static final long serialVersionUID = 6738763469069805372L;

    // 番号
    @Id
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

    //更新时间
    private LocalDate createTime;

    //更新时间
    private LocalDateTime updateTime;
}
