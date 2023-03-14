package com.ding.boots.service;

import com.ding.boots.dao.SignDao;
import com.ding.boots.dto.RegisterDTO;
import com.ding.boots.dto.SignInDTO;
import com.ding.common.exception.ServiceException;
import com.ding.common.utils.JWTUtils;
import com.ding.common.utils.UUIDUtils;
import com.ding.common.utils.json.ApiResult;
import com.ding.common.utils.json.StatusCodeEnum;
import com.ding.domain.Account;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author ding
 * @create 27 21:24
 * @description
 */
@Service
public class SignServiceImpl implements SignService{
    private Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);
    @Resource
    private SignDao signDao;
    @Override
    public Map<String,Object> signIn(SignInDTO signInDTO) {
        String password = signInDTO.getPassword();
        if (Optional.ofNullable(signInDTO.getAccount()).isEmpty()&&
                Optional.ofNullable(signInDTO.getEmail()).isEmpty()&&
                Optional.ofNullable(signInDTO.getMobileNumber()).isEmpty()){
            throw new ServiceException(ApiResult.failed(StatusCodeEnum.SIGN_IN.getCode(),StatusCodeEnum.SIGN_IN.getMessage()));
        }
        Account account = signDao.signIn(signInDTO);
        if (!password.equals(account.getPassword())){
            throw new ServiceException(ApiResult.failed(StatusCodeEnum.SIGN_IN.getCode(),StatusCodeEnum.SIGN_IN.getMessage())) ;
        }
        //将userId存入token中
        String token = JWTUtils.createToken(account.getUid());
        logger.info("login success. token:{}",token);
        Map<String,Object> map = new HashMap<>();
        map.put("account",account);
        map.put("token",token);
        return map;
    }

    @Override
    public String signOut() {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult register(RegisterDTO registerDTO) {
        if (Optional.ofNullable(registerDTO.getAccount()).isEmpty()&&
                Optional.ofNullable(registerDTO.getEmail()).isEmpty()&&
                Optional.ofNullable(registerDTO.getMobileNumber()).isEmpty()){
            return ApiResult.failed(StatusCodeEnum.SIGN_IN.getCode(),StatusCodeEnum.SIGN_IN.getMessage());
        }
        Account account = new Account();
        BeanUtils.copyProperties(registerDTO,account);
        account.setUid(UUIDUtils.createUUID());
        account.setSalt(UUIDUtils.createUUID());
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        Integer size = signDao.register(account);
        if (!Optional.ofNullable(size).isPresent()&&size==0){
            return ApiResult.failed();
        }
        return ApiResult.success();
    }
}
