package com.ding.boots.dao;

import com.ding.domain.sys.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ding
 * @create 28 19:05
 * @description
 */
@Mapper
public interface SysRoleDao {

    @Select("select * from sys_role")
    List<SysRole> roleList();

    @Insert({"insert into sys_role(rid,parentRid,roleName,createTime,updateTime,description) values" ,
            "(#{rid},#{parentRid},#{roleName},#{createTime},#{updateTime},#{description})",
            ""})
    Integer addRole(SysRole sysRole);
    @Delete("delete from sys_role where rid=#{id}")
    Integer deleteRole(Integer id);

    @Update({"<script>update sys_role set " ,
            "<if test='parentRid!=null'>type=#{parentRid},</if> " ,
            "<if test='roleName!=null and roleName!=\"\"'>roleName=#{roleName} ,</if> " ,
            "updatetime=now() ,",
            "<if test='description!=null and description!=\"\"'>description=#{description}</if> " ,
            "where rid=#{rid}</script>"})
    Integer updateRole(SysRole sysRole);
}
