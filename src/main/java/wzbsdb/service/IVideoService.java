package wzbsdb.service;

import wzbsdb.dataobject.Video;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/9/20
 * @since 1.0.0
 */
public interface IVideoService {
    /**
     * 番号是否已存在
     * @param designation
     * @return
     */
    boolean isExist(String designation);

    /**
     * 保存video
     * @param video
     */
    void save(Video video);
}
