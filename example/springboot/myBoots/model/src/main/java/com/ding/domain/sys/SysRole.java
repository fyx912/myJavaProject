package com.ding.domain.sys;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName SysRole
 * @date 2020-03-09 11:11
 */
@Schema(name = "角色实体类",description = "角色")
public class SysRole {
    @Schema(title = "角色ID", required = false, example = "4")
    private Integer rid;    //角色ID
    @Schema(title = "父ID", required = false, example = "4")
    private Integer parentRid;
    @Schema(title = "角色名称", required = true)
    private String roleName;   //角色名称
    @Schema(title = "创建时间", required = false)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date createTime;   //创建时间
    @Schema(title = "更新时间", required = false)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date updateTime ;  //更新时间
    @Schema(title = "描述", required = false)
    private String description; //描述

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getParentRid() {
        return parentRid;
    }

    public void setParentRid(Integer parentRid) {
        this.parentRid = parentRid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
