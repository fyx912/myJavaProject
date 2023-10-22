package com.ding.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>  </p>
 *
 * @author Tintin
 * @date 2023-10-22 15:29:38
 **/

public class SignInDTO {
    private String account;
    private String passwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
