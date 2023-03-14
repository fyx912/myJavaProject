package com.ding.order;

import com.ding.BaseTest;
import com.ding.domain.order.Task;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ding
 * @create 28 21:56
 * @description
 */
public class TaskTest extends BaseTest {

    @Test
    public void testList() throws Exception {
        this.mockTest(HttpMethod.GET,"/task/list",null);
    }


    @Test
    public void testAdd() throws Exception {
        Task task = new Task();
        task.setCid("5fba966d197a4cd586e2f2f05961f47b");
        task.setTaskName("手机活动");
        task.setTaskNumber(5);
        task.setStartTime(new Date());
        task.setEndTime(new Date());
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        task.setTaskDescription("活动快开始了.....");
        this.mockTest(HttpMethod.POST,"/task/add",task);
    }

    @Transactional
    @Rollback
    @Test
    public void testDelete() throws Exception {
        this.mockTest(HttpMethod.DELETE,"/task/24999ae3218f4ef98ca30ea5bda7713c",null);
    }

    @Transactional
    @Rollback
    @Test
    public void testUpdate() throws Exception {
        Task task = new Task();
        task.setTid("0842197378254bbf9c75a97b98fbf981");
        task.setCid("5fba966d197a4cd586e2f2f05961f47b");
        task.setTaskName("手机活动");
        task.setTaskNumber(5);
        task.setStartTime(new Date());
        task.setEndTime(new Date());
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        task.setTaskDescription("活动快开始了.....");
        this.mockTest(HttpMethod.POST,"/task/update",task);
    }
}
