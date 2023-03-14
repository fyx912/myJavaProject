package com.ding.boots.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * @author ding
 * @create 27 23:54
 * @description
 */
@Schema(name = "注册实体类",description = "注册")
public class RegisterDTO{
    @Schema(title = "账号", required = true, example = "tintin")
    private String account;
    @Schema(title = "手机号码", required = true, example = "20")
    @Length(max = 11,message = "手机号码有误!")
    private String mobileNumber;
    @Schema(title = "email", required = true, example = "admin@163.com")
    @Email(message = "必须是email格式")
    private String email;
    @Schema(title = "密码", required = true, example = "123")
    @NotNull(message = "密码不能空!")
    private String password;
    @Schema(title = "昵称", required = true, example = "123")
    private String nickName;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
