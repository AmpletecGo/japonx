package com.wzbsdb.service.impl;

import com.wzbsdb.dataobject.Task;
import com.wzbsdb.repository.ITaskRepository;
import com.wzbsdb.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/10/16
 * @since 1.0.0
 */
@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public boolean isExist(String start) {
        Task task = taskRepository.findByStart(start);
        if (ObjectUtils.isEmpty(task)){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }
}
