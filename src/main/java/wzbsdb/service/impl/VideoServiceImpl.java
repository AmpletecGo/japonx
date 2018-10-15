package wzbsdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import wzbsdb.dataobject.Video;
import wzbsdb.repository.IVideoRepository;
import wzbsdb.service.IVideoService;

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
    public boolean isExist(String designation) {
        Video video = videoRepository.findByDesignation(designation);
        if (ObjectUtils.isEmpty(video)){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void save(Video video) {
        videoRepository.save(video);
    }
}
