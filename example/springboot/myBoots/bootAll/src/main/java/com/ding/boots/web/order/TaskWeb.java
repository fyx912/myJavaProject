package com.ding.boots.web.order;

import com.ding.boots.service.order.TaskService;
import com.ding.common.utils.json.ApiResult;
import com.ding.domain.order.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author ding
 * @create 28 18:24
 * @description
 */
@Tag(name = "TaskWeb",description = "任务")
@RestController
@RequestMapping(value = "task",produces = "application/json;charset=UTF-8")
public class TaskWeb {
    @Resource
    private TaskService taskService;

    @GetMapping("list")
    @Operation(summary = "获取任务列表接口")
    private ApiResult getTaskList(){
        return taskService.taskList();
    }

    @PostMapping("add")
    @Operation(summary = "新增任务接口")
    private ApiResult addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @PostMapping("update")
    @Operation(summary = "修改任接口")
    private ApiResult updateTask(@RequestBody Task task){
        return taskService.updateTask(task);
    }

    @DeleteMapping("{tid}")
    @Operation(summary = "删除任务接口")
    private ApiResult deleteTask(@PathVariable("tid")String tid){
        return taskService.deleteTask(tid);
    }
}

