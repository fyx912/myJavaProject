package com.ding.boots.service.sys;

import com.ding.boots.dao.SysRoleDao;
import com.ding.common.utils.json.ApiResult;
import com.ding.domain.sys.SysRole;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author ding
 * @create 28 19:04
 * @description
 */
@Service
public class SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    public ApiResult roleList(){
        List<SysRole> list =  sysRoleDao.roleList();
        return ApiResult.success(list);
    }

    @Transactional(rollbackFor = Exception.class)
    public ApiResult addRole(SysRole sysRole){
        if (!Optional.ofNullable(sysRole).isPresent()){
            return ApiResult.failed();
        }
        sysRoleDao.addRole(sysRole);
        return ApiResult.success();
    }
    @Transactional(rollbackFor = Exception.class)
    public ApiResult deleteRole(Integer id){
        sysRoleDao.deleteRole(id);
        return ApiResult.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ApiResult updateRole(SysRole sysRole){
        sysRoleDao.updateRole(sysRole);
        return ApiResult.success();
    }
}
