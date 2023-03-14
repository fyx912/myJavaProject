package com.ding.boots.service;

import com.ding.boots.dto.RegisterDTO;
import com.ding.boots.dto.SignInDTO;
import com.ding.common.utils.json.ApiResult;

import java.util.Map;

/**
 * @author ding
 * @create 27 21:22
 * @description
 */
public interface SignService {
    Map<String,Object> signIn(SignInDTO signInDTO);
    String signOut();
    ApiResult register(RegisterDTO registerDTO);
}
