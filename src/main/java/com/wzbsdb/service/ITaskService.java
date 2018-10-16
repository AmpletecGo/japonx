package com.wzbsdb.service;

import com.wzbsdb.dataobject.Task;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/10/16
 * @since 1.0.0
 */
public interface ITaskService {
    /**
     * 是否已存在
     * @param start
     * @return
     */
    boolean isExist(String start);

    /**
     * 保存记录
     * @param task
     */
    void save(Task task);
}
