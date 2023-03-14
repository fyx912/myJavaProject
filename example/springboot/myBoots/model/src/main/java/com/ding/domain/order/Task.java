package com.ding.domain.order;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * @author ding
 * @create 27 18:57
 * @description
 */
@Schema(name = "任务实体类",description = "任务")
public class Task {
    @Schema(title = "任务id", required = true)
    private String tid;
    @Schema(title = "商品id", required = true)
    private String cid;
    @Schema(title = "任务名称", required = true)
    private String taskName;
    @Schema(title = "任务数量", required = true)
    private Integer taskNumber;
    //json
    @Schema(title = "任务信息json", required = false)
    private String  info;
    //任务开始时间
    @Schema(title = "任务开始时间", required = true)
    private Date startTime;
    //任务结束时间
    @Schema(title = "任务结束时间", required = true)
    private Date endTime;
    //创建时间
    @Schema(title = "任务创建时间", required = false)
    private Date createTime;
    //更新时间
    @Schema(title = "更新时间", required = false)
    private Date updateTime;
    @Schema(title = "描述", required = false)
    private String taskDescription; //描述

    public Task() {
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
