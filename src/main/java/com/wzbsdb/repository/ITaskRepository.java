package com.wzbsdb.repository;

import com.wzbsdb.dataobject.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/10/16
 * @since 1.0.0
 */
public interface ITaskRepository extends JpaRepository<Task, Integer> {
    Task findByStart(String start);
}
