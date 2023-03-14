package com.ding.boots.web;

import com.ding.boots.service.AccountService;
import com.ding.common.config.UserLoginToken;
import com.ding.common.utils.json.ApiResult;
import com.ding.domain.Account;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author ding
 * @create 27 17:49
 * @description
 */
@Tag(name ="AccountWeb",description = "账号")
@RestController
@RequestMapping(value = "account",produces = "application/json;charset=UTF-8")
public class AccountWeb {
    @Resource
    private AccountService accountService;

    @Operation(summary ="获取账号列表接口",description = "返回分页对象集合")
    @UserLoginToken
    @GetMapping("list")
    @ApiResponse(responseCode = "2xx",description = "获取账号列表接口")
    public ApiResult  accountList(int pageNum,int pageSize){
        PageHelper.startPage(1,1);
        List<Account> list =  accountService.accountList(null);
        return ApiResult.success(new PageInfo<Account>(list));
    }
}
