package com.ding.doman.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>  </p>
 *
 * @author Tintin
 * @date 2023-10-22 00:49:16
 **/

@Table("sys_role")
@Data
@Getter
@Setter
public class Role implements Serializable {
    @Id
    @Column("s_r_id")
    @JsonProperty("srId")
    //角色主键ID
    private Integer srId;
    //角色名称
    @Column("role_name")
    @JsonProperty("roleName")
    private String roleName;
    //类型(1系统角色2自定义角色)
    @JsonProperty("type")
    private Integer type;
    //状态(1正常2停用)
    @JsonProperty("status")
    private Integer status;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalDateTimeSerializer.class)		// 序列化
    private LocalDateTime createTime;

    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalDateTimeSerializer.class)		// 序列化
    private LocalDateTime updateTime;
    //描述
    @JsonProperty("remark")
    private String remark;

}
