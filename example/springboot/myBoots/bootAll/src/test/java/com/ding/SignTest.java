package com.ding;

import com.ding.boots.dto.RegisterDTO;
import com.ding.boots.dto.SignInDTO;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ding
 * @create 28 1:32
 * @description
 */
public class SignTest extends  BaseTest{

    @Test
    public void testLogin() throws Exception {
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setAccount("appUser");
        signInDTO.setPassword("123456");
        this.mockTest(HttpMethod.POST,"/login",signInDTO);

        SignInDTO signInDTO2 = new SignInDTO();
        signInDTO.setMobileNumber("11100000003");
        signInDTO.setPassword("123456");
        this.mockTest(HttpMethod.POST,"/login",signInDTO2);

        SignInDTO signInDTO3 = new SignInDTO();
        signInDTO.setEmail("appuser@user.com");
        signInDTO.setPassword("123456");
        this.mockTest(HttpMethod.POST,"/login",signInDTO3);
    }


    @Transactional
    @Rollback
    @Test
    public void testRegister() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setAccount("appuser");
        registerDTO.setMobileNumber("11100000003");
        registerDTO.setEmail("appuser@user.com");
        registerDTO.setPassword("123456");
        registerDTO.setNickName("appUser");

        this.mockTest(HttpMethod.POST,"/register",registerDTO);
    }
}
