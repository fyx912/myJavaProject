package com.ding.boots.dao;


import com.ding.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ding
 * @create 27 21:10
 * @description
 */
@Mapper
public interface AccountDao {
    @Select("select * from u_account where account ='admin' and password='123456'")
    Account login();

    @Select("select * from u_account")
    List<Account> accountList();
}
