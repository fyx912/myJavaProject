package com.ding.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author ding
 * @create 14 23:59
 * @description
 */
@Slf4j
@Component
public class MyDataFlowJob implements DataflowJob<String> {
    private boolean flag = false;
    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        log.info("开始获取数据");
        if (flag) {
            return null;
        }
        // 获取到了数据
        return Arrays.asList("ding","fyx912","tintin");
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> data) {
        for (String val : data) {
            // 处理完数据要移除掉，不然就会一直跑,处理可以在上面的方法里执行。这里采用 flag
            log.info("开始处理数据：" + val);
        }
        flag = true;
    }
}
