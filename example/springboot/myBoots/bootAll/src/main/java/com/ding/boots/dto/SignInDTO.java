package com.ding.boots.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

/**
 * @author ding
 * @create 27 21:25
 * @description
 */
@Schema(name = "登录实体类",description = "登录")
public class SignInDTO implements Serializable {

    @Schema(title = "账号", required = true, example = "admin")
    private String account;
    @Schema(title = "email", required = true, example = "admin@163.com")
    @Email(message = "必须是email格式")
    private String email;
    @Schema(title = "手机号码", required = true, example = "136********")
    @Length(max = 11,message = "手机号码有误!")
    private String mobileNumber;
    @Schema(title = "密码", required = true)
    @NotNull(message = "密码不能空!")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
