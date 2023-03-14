package com.ding.boots.web.sys;

import com.ding.boots.service.sys.SysRoleService;
import com.ding.common.utils.json.ApiResult;
import com.ding.domain.sys.SysRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author ding
 * @create 27 17:48
 * @description
 */
@Tag(name = "SysRoleWeb",description = "角色")
@RestController
@RequestMapping(value = "sys/sysRole",produces = "application/json;charset=UTF-8")
public class SysRoleWeb {
    @Resource
    private SysRoleService roleService;

    @GetMapping("list")
    @Operation(summary = "获取角色列表接口",description ="返回角色集合" )
    private ApiResult getSysRoleList(){
        return roleService.roleList() ;
    }

    @PostMapping("add")
    @Operation(summary = "新增角色接口")
    private ApiResult addRole(@RequestBody SysRole sysRole){
        return roleService.addRole(sysRole) ;
    }

    @PostMapping("delete/{id}")
    @Operation(summary = "删除角色接口")
    private ApiResult deleteRole(@PathVariable("id")  Integer id){
        return roleService.deleteRole(id) ;
    }

    @PostMapping("update")
    @Operation(summary = "修改角色接口")
    private ApiResult updateRole(@RequestBody SysRole sysRole){
        return roleService.updateRole(sysRole);
    }

}
