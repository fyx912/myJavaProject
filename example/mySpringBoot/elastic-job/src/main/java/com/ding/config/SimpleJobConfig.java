//package com.ding.config;
//
//import com.dangdang.ddframe.job.api.simple.SimpleJob;
//import com.dangdang.ddframe.job.config.JobCoreConfiguration;
//import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
//import com.dangdang.ddframe.job.lite.api.JobScheduler;
//import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
//import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
//import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
//import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
//import com.ding.job.MySimpleJob;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//
//@Configuration
//@PropertySource("classpath:job.properties")
//public class SimpleJobConfig {
//
//    @Autowired
//    private ZookeeperRegistryCenter regCenter;
//
//    /**
//     * 配置任务监听器
//     * @return
//     */
//    @Bean
//    public ElasticJobListener elasticJobListener() {
//        return new MyElasticJobListener();
//    }
//
//    /**
//     * 配置任务详细信息
//     * @param jobClass
//     * @param cron
//     * @param shardingTotalCount
//     * @param shardingItemParameters
//     * @return
//     */
//    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
//                                                         final String cron,
//                                                         final int shardingTotalCount,
//                                                         final String shardingItemParameters) {
//        // 定义作业核心配置
//        return LiteJobConfiguration.newBuilder(
//                // 定义SIMPLE类型配置
//                new SimpleJobConfiguration(
//                        // 定义Lite作业根配置
//                        JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
//                                .shardingItemParameters(shardingItemParameters).build(),
//                        jobClass.getCanonicalName()
//                )
//        ).overwrite(true).build();
//    }
//
//
//    @Bean(initMethod = "init")
//    public JobScheduler simpleJobScheduler(final MySimpleJob mySimpleJob,
//                                           @Value("${simpleJob.cron}") final String cron,
//                                           @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount,
//                                           @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters) {
//        MyElasticJobListener elasticJobListener = new MyElasticJobListener();
//        return new SpringJobScheduler(mySimpleJob, regCenter,
//                getLiteJobConfiguration(mySimpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters),
//                elasticJobListener);
//    }
//}
