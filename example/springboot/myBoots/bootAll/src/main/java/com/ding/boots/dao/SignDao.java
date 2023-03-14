package com.ding.boots.dao;

import com.ding.boots.dto.SignInDTO;
import com.ding.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ding
 * @create 27 21:29
 * @description
 */
@Mapper
public interface SignDao {

    @Select({"<script>" ,
            "select * from u_account " ,
            "<where>" ,
            "<when test='account!=null and account!=\"\"'>account=#{account}</when>" ,
            "<when test='mobileNumber!=null and mobileNumber!=\"\"'>and mobileNumber=#{mobileNumber}</when>" ,
            "<when test='email!=null and email!=\"\"'>and email=#{email}</when>",
            "and status=1",
            "</where>" ,
            "</script>"})
    Account signIn(SignInDTO signInDTO);

    @Insert({"insert into u_account(uid,account,mobileNumber,email,password,nickName,salt,createTime,updateTime) values" ,
            "(#{uid},#{account},#{mobileNumber},#{email},#{password},#{nickName},#{salt},#{createTime},#{updateTime})",
            ""})
    Integer register(Account account);
}
