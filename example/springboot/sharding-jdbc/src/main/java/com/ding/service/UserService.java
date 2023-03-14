package com.ding.service;

import com.ding.dao.UserDao;
import com.ding.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ding
 * @create 20230314 0:07
 * @description
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public Integer insert(User user){
        return userDao.insert(user);
    }

    public Integer update(User user){
        return userDao.update(user);
    }

    public Integer delete(String uid){
        return userDao.delete(uid);
    }

    public User findUserByUid(String uid){
        return userDao.findUserByUid(uid);
    }
}
