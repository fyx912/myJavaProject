package com.ding.test;

import com.ding.SharDingJdbcMain;
import com.ding.entity.User;
import com.ding.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ding
 * @create 20230314 0:12
 * @description
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = SharDingJdbcMain.class)
public class UserTest {
    private Logger logger = LoggerFactory.getLogger(UserTest.class);
    @Autowired
    private UserService userService;
    private Map map = new HashMap();
    private ObjectMapper objectWriter = new  ObjectMapper();
    @BeforeAll
    void json(){
        map.put("code",0);
        map.put("msg","success");
    }

    @Test
    public void findAll() throws JsonProcessingException {
        List<User> list = userService.findAll();
        map.put("data",list);
        String json = objectWriter.writeValueAsString(map);
        logger.info("data{}",json);
    }

//    @Transactional(rollbackFor = Exception.class)
    @Test
    public void insert(){
        User user = new User();
        user.setUid("4");
        user.setAccount("jack");
        user.setNickname("杰克");
        user.setPasswd("1223456");
        user.setStatus(1);
        user.setPhoneNumber("1360000005");
        user.setTotalIntegral(0L);
        user.setTotalMoney(BigDecimal.ONE);
        user.setLastTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userService.insert(user);
    }


    @Test
    public void update()  {
        User user= new User();
        user.setUid("4");
        user.setAccount("jack");
        user.setNickname("杰克");
        user.setPasswd("1223456");
        user.setStatus(1);
        user.setPhoneNumber("1360000005");
        user.setTotalIntegral(0L);
        user.setTotalMoney(BigDecimal.ONE);
        user.setUpdateTime(LocalDateTime.now());
        user.setLastTime(LocalDateTime.now());
        userService.update(user);
    }

    @Test
    public void delete(){
        userService.delete("4");
    }

    @Test
    public void findUserByUid() throws JsonProcessingException {
        User user = userService.findUserByUid("1");
        map.put("data",user);
        String json = objectWriter.writeValueAsString(map);
        logger.info("data{}",json);
    }


}
