package com.wzbsdb.repository;

import com.wzbsdb.dataobject.Video;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/9/20
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class VideoRepositoryTest {

    @Autowired
    private IVideoRepository repository;

    @Test
    public void findByDesignation(){
        Video video1 = repository.findByDesignation("ipz");
        log.info(video1.toString());
    }

    @Test
    public void save(){
        Video video = new Video();
        video.setName("123");
        video.setDescription("456");
        video.setDesignation("ipz");
        video.setVideoUrl("456465");
        video.setIndexUrl("wqreqreq");
        video.setCreateTime(LocalDate.now());
        Video video1 = repository.save(video);
        log.info(video1.toString());
    }

}