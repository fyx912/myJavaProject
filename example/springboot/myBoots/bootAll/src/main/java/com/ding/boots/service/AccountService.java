package com.ding.boots.service;

import com.ding.boots.dao.AccountDao;
import com.ding.domain.Account;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author ding
 * @create 27 21:16
 * @description
 */
@Service
public class AccountService {
    @Resource
    private AccountDao accountDao;

    public List<Account> accountList(String account){
        return accountDao.accountList();
    }
}
