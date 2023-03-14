package com.ding.sys;

import com.ding.BaseTest;
import com.ding.domain.sys.SysRole;
import org.junit.Test;
import org.springframework.http.HttpMethod;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName RoleTest
 * @date 2020-03-09 13:52
 */
public class RoleTest extends BaseTest {

    @Test
    public void testList() throws Exception {
        this.mockTest(HttpMethod.GET,"/sysRole/list",null);
    }

    @Test
    public void testAdd() throws Exception {
        SysRole sysRole = new SysRole();
        sysRole.setRid(4);
        sysRole.setRoleName("运营人员");
        this.mockTest(HttpMethod.POST,"/sysRole/add",sysRole);
    }

    @Test
    public void testDelete() throws Exception {
        this.mockTest(HttpMethod.POST,"/sysRole/delete/4",null);
    }
}
