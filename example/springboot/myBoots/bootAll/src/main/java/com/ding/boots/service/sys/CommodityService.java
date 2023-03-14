package com.ding.boots.service.sys;

import com.ding.boots.dao.CommodityDao;
import com.ding.common.utils.UUIDUtils;
import com.ding.common.utils.json.ApiResult;
import com.ding.domain.sys.Commodity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author ding
 * @create 28 18:22
 * @description
 */
@Service
public class CommodityService {
    @Resource
    CommodityDao commodityDao;

    public ApiResult commodityList(){
        List<Commodity> list = commodityDao.commodityList();
        return ApiResult.success(list);
    }


    @Transactional(rollbackFor = Exception.class)
    public ApiResult addCommodity(Commodity commodity){
        if (!Optional.ofNullable(commodity).isPresent()){
            return ApiResult.failed();
        }
        commodity.setCid(UUIDUtils.createUUID());
        commodity.setCreateTime(new Date());
        commodity.setUpdateTime(new Date());
        commodityDao.addCommodity(commodity);
        return ApiResult.success();
    }
    @Transactional(rollbackFor = Exception.class)
    public ApiResult deleteCommodity(String id){
        commodityDao.deleteCommodity(id);
        return ApiResult.success();
    }
    @Transactional(rollbackFor = Exception.class)
    public ApiResult update(Commodity commodity){
        if (!Optional.ofNullable(commodity).isPresent()){
            return ApiResult.failed();
        }
        commodityDao.update(commodity);
        return ApiResult.success();
    }
}
