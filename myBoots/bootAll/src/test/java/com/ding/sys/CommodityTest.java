package com.ding.sys;

import com.ding.BaseTest;
import com.ding.domain.enums.CommodityTypeEnum;
import com.ding.domain.sys.Commodity;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;

/**
 * @author ding
 * @create 28 20:47
 * @description
 */
public class CommodityTest extends BaseTest {

    @Test
    public void testList() throws Exception {
        this.mockTest(HttpMethod.GET,"/commodity/list",null);
    }


    @Test
    public void testAdd() throws Exception {
        Commodity commodity = new Commodity();
        commodity.setType(CommodityTypeEnum.HOME_APPLIANCES.getType());
        commodity.setCommodityName("苏氏陶瓷（SUSHI CERAMICS）");
        commodity.setRetailPrice(new BigDecimal( 159.00));
        commodity.setStock(100);
        commodity.setDescription("茶具套装新窑变银丝釉泡茶碗苹果功夫茶杯陶瓷三才盖碗13头礼盒装");
        this.mockTest(HttpMethod.POST,"/commodity/add",commodity);
    }

    @Test
    public void testDelete() throws Exception {
        this.mockTest(HttpMethod.POST,"/commodity/delete/9e54f2431456453583d263f431f51037",null);
    }

    @Test
    public void testUpdate() throws Exception {
        Commodity commodity = new Commodity();
        commodity.setCid("5fba966d197a4cd586e2f2f05961f47b");
        commodity.setType(CommodityTypeEnum.ELECTRONIC_PRODUCTS.getType());
        commodity.setCommodityName("Apple iPhone 13 (A2634)");
        commodity.setRetailPrice(new BigDecimal( 6799.00));
        commodity.setStock(10);
        commodity.setDescription("Apple iPhone 13 (A2634) 256GB 星光色 支持移动联通电信5G 双卡双待手机");
        this.mockTest(HttpMethod.PUT,"/commodity/update",commodity);
    }
}
