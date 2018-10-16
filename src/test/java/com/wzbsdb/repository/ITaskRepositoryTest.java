package com.wzbsdb.repository;

import com.wzbsdb.dataobject.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/10/16
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITaskRepositoryTest {

    @Autowired
    private ITaskRepository repository;

    @Test
    public void findByDesignation(){
        Task task = repository.findByStart("123");
        System.out.println(task);
    }

    @Test
    public void save(){
        Task task = new Task();
        task.setStart("89");
        task.setStop("321654");
        task.setCount(22);
        task.setStartTime(LocalDateTime.now());
        task.setStopTime(LocalDateTime.now());
        repository.save(task);
        System.out.println(task);
    }
}
