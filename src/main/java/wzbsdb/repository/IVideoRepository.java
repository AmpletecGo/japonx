package wzbsdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wzbsdb.dataobject.Video;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/9/20
 * @since 1.0.0
 */
public interface IVideoRepository extends JpaRepository<Video, Integer> {
    Video findByDesignation(String designation);
}
