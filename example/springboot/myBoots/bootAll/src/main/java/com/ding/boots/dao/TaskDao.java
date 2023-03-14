package com.ding.boots.dao;

import com.ding.domain.order.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ding
 * @create 28 21:34
 * @description
 */
@Mapper
public interface TaskDao {

    List<Task> taskList();
    Integer addTask(Task task);
    Integer updateTask(Task task);
    Integer deleteTask(String tid);
}
