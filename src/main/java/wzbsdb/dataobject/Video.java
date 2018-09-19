package wzbsdb.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/9/14
 * @since 1.0.0
 */
@Data
public class Video implements Serializable {

    private static final long serialVersionUID = 6738763469069805372L;

    // 影片名称
    private String name;

    // 番号
    private String designation;

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
}
