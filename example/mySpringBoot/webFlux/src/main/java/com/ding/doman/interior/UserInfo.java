package com.ding.doman.interior;

import lombok.Getter;
import lombok.Setter;

/**
 * <p> userInfo Json 用户详情json对象 </p>
 *
 * @author Tintin
 * @date 2023-10-21 23:54:11
 **/
@Getter
@Setter
public class UserInfo {
    //省份
    private String provide;
    //城市
    private String city;
    //详细地址
    private String address;
    //性别1男2女3未知
    private Integer gender;

}
