package wzbsdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wzbsdb.dataobject.Video;
import wzbsdb.repository.IVideoRepository;
import wzbsdb.service.IVideoService;

import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/9/20
 * @since 1.0.0
 */
@Service
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private IVideoRepository videoRepository;

    @Override
    public List<Video> saveAll(Set<Video> videoList) {
        List<Video> videoListSave = videoRepository.saveAll(videoList);
        return videoListSave;
    }
}
