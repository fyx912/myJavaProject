package com.ding.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author ding
 * @create 27 18:18
 * @description
 */
@Data
public class UserInfo {
    private String uid;
    private String name;
    private Integer gender;
    private Date birthday;
    private String address;
    private String personalizedSignature;
}
