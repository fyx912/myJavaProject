package com.ding.boots.service.order;

import com.ding.boots.dao.TaskDao;
import com.ding.common.utils.UUIDUtils;
import com.ding.common.utils.json.ApiResult;
import com.ding.domain.order.Task;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author ding
 * @create 28 18:25
 * @description
 */
@Service
public class TaskService {
    @Resource
    private TaskDao taskDao;
    public ApiResult taskList(){
        List<Task> list = taskDao.taskList();
        return ApiResult.success(list);
    }

    public ApiResult addTask(Task task){
        task.setTid(UUIDUtils.createUUID());
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        taskDao.addTask(task);
        return ApiResult.success();
    }

    public ApiResult updateTask(Task task){
        taskDao.updateTask(task);
        return ApiResult.success();
    }

    public ApiResult deleteTask(String tid){
        taskDao.deleteTask(tid);
        return ApiResult.success();
    }
}
