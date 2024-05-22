package com.ding.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName FlowRuleConfig
 * @date 2022-01-25 16:18
 */
@Configuration
public class FlowRuleConfig {
    public FlowRuleConfig(){
        // 代码配置限流
        FlowRule flowRule = new FlowRule();
        // 限流资源接口
        flowRule.setResource("com.ding.api.service.HelloService:hello(java.lang.String)");
        // 限流数峰值
        flowRule.setCount(3);
        // QPS限流
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setLimitApp("default");
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
    }
}
