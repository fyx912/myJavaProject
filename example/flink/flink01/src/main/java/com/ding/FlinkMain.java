package com.ding;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * FlinkMain
 *
 * @author ding
 * @version 1.0
 * @description
 * @date 2023-04-19:22:37
 */
public class FlinkMain {
    public static void main(String[] args) throws Exception {
        String hostname="127.0.0.1";
        int port=9000;
        // 设置运行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //获取数据源
        DataStream stream = env.socketTextStream(hostname,port);
        //计数
        SingleOutputStreamOperator sum = stream.flatMap((new LineSplitter())).keyBy(0).sum(1);
        //输出
        sum.print();
        //执行
        env.execute("Java hello Word from SocketTextStream Example");
    }
    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] tokens = s.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    collector.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }
}
