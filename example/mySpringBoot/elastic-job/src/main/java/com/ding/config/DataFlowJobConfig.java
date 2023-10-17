package com.ding.config;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.ding.job.MyDataFlowJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ding
 * @create 15 0:12
 * @description
 */
@Configuration
@PropertySource("classpath:job.properties")
public class DataFlowJobConfig {
    @Autowired
    private ZookeeperRegistryCenter regCenter;
    /**
     * 配置任务详细信息
     * @param jobClass
     * @param jobCron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @return
     */
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends DataflowJob> jobClass,
                                                         final String jobCron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters) {
        // 定义作业核心配置
        JobCoreConfiguration dataflowCoreConfig = JobCoreConfiguration.newBuilder(jobClass.getName(), jobCron, shardingTotalCount).
                shardingItemParameters(shardingItemParameters).build();
        // 定义DATAFLOW类型配置
        DataflowJobConfiguration dataflowJobConfig = new DataflowJobConfiguration(dataflowCoreConfig, jobClass.getCanonicalName(), false);
        // 定义Lite作业根配置
        LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataflowJobConfig).overwrite(true).build();
        return dataflowJobRootConfig;
    }


    @Bean(initMethod = "init")
    public JobScheduler dataJobScheduler(final MyDataFlowJob myDataFlowJob,
                                           @Value("${dataFlowJob.cron}") final String cron,
                                           @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters) {
        MyElasticJobListener elasticJobListener = new MyElasticJobListener();
        return new SpringJobScheduler(myDataFlowJob, regCenter,
                getLiteJobConfiguration(myDataFlowJob.getClass(), cron, shardingTotalCount, shardingItemParameters),
                elasticJobListener);
    }
}
