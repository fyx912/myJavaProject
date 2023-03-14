package com.ding.dao;

import com.ding.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ding
 * @create 2023-03-1313 22:36
 * @description
 */
@Mapper
public interface UserDao {

    @Select("select * from u_user")
    public List<User> findAll();

    @Select("select * from u_user where uid=#{uid}")
    public User findUserByUid(String uid);

    @Insert({"insert into u_user(uid,phone_number,account,nickname,status,total_money,total_integral,passwd,last_time,create_time,update_time)" +
            "VALUES(#{uid},#{phoneNumber},#{account},#{nickname},#{status,jdbcType=INTEGER}" +
            ",#{totalMoney,jdbcType=DECIMAL},#{totalIntegral,jdbcType=BIGINT},#{passwd}," +
            "#{lastTime,jdbcType=TIMESTAMP},#{createTime,jdbcType=TIMESTAMP},#{updateTime,jdbcType=TIMESTAMP}) "})

    @Options(useGeneratedKeys = true, keyProperty = "uid")
    public Integer insert(User user);

    @Update("<script>" +
            "update u_user " +
            "<trim prefix='set' suffixOverrides=','>"+
            "<if test=\"phoneNumber!=null and phoneNumber !=''\">phone_number=#{phoneNumber},</if>"+
            "<if test=\"account!=null and account !=''\"> account=#{account},</if>"+
            "<if test=\"nickname!=null and nickname !=''\"> nickname=#{nickname},</if>"+
            "<if test=\"status!=null\">status=#{status,jdbcType=INTEGER},</if>"+
            "<if test=\"totalMoney!=null\">total_money=#{totalMoney,jdbcType=DECIMAL},</if>"+
            "<if test=\"totalIntegral!=null\">total_integral=#{totalIntegral,jdbcType=BIGINT},</if>"+
            "<if test=\"passwd!=null and passwd !=''\"> passwd=#{passwd},</if>"+
            "<if test=\"lastTime!=null \">last_time=#{lastTime,jdbcType=TIMESTAMP},</if>"+
            "<if test=\"createTime!=null \">create_time=#{createTime,jdbcType=TIMESTAMP},</if>"+
            "<if test=\"updateTime!=null \">update_time=#{updateTime,jdbcType=TIMESTAMP}</if>"+
            "</trim> WHERE  uid=#{uid}"+
            "</script>")
    public Integer update(User user);

    @Delete("delete from u_user where uid=#{uid}")
    public Integer delete(String uid);
}
