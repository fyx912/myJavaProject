package com.ding.boots.dao;

import com.ding.domain.sys.Commodity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ding
 * @create 28 20:01
 * @description
 */
@Mapper
public interface CommodityDao {

    @Select("select * from sys_commodity")
    List<Commodity> commodityList();

    @Insert({"insert into sys_commodity(cid,type,commodityName,retailPrice,stock,createTime,updateTime,description) values" ,
            "(#{cid},#{type},#{commodityName},#{retailPrice},#{stock},#{createTime},#{updateTime},#{description})",
            ""})
    Integer addCommodity(Commodity commodity);

    @Delete("delete from sys_commodity where cid=#{cid}")
    Integer deleteCommodity(String cid);

    @Update({"<script>update sys_commodity set " ,
            "<if test='type!=null'>type=#{type},</if> " ,
            "<if test='commodityName!=null and commodityName!=\"\"'>commodityName=#{commodityName} ,</if> " ,
            "<if test='retailPrice!=null'>retailPrice=#{retailPrice},</if> " ,
            "<if test='stock!=null'>stock=#{stock},</if> " ,
            "updatetime=now() ,",
            "<if test='description!=null and description!=\"\"'>description=#{description}</if> " ,
            "where cid=#{cid}</script>"})
    Integer update(Commodity commodity);
}
