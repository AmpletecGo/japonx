package wzbsdb.service;

import wzbsdb.dataobject.Video;

import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/9/20
 * @since 1.0.0
 */
public interface IVideoService {
    List<Video> saveAll(Set<Video> videoList);
}
