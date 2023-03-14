package com.ding.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * 雪花算法
 * @author ding
 * @create 12 22:38
 * @description
 */
public class SnowFlakeUtils {
    //起始时间戳( 2020-12-26 00:00:00 )
    private static final long START_STAMP = 1608912000000L;

    //序列号占用位数
    private static final long SEQUENCE_BIT = 12;

    //机器标识占用位数
    private static final long MACHINE_BIT = 5;

    //数据中心占用位数
    private static final long DATACENTER_BIT = 5;

    //序列号最大值
    private static final long MAX_SEQUENCE = -1L ^ (-1L << 12); // 4095

    /**
     * 偏移量
     **/
    private static final long MACHINE_LEFT = SEQUENCE_BIT;

    private static final long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    private static final long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private static long dataCenterId; //0,  数据中心(0-31)
    private static long machineId; //0,  机器标识(0-31)

    private static long sequence; //序列号  range(0 ~ 4095)
    private static long lastStamp; //上一次时间戳

    public static synchronized long getNextId() {

        long currentStamp = System.currentTimeMillis();

        if (currentStamp < lastStamp) {
            throw new IllegalArgumentException("时间被回退，不能继续产生id");
        }

        if (currentStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                //序列号已经到最大值
                System.out.println("序列号已经到达最大值");
                //使用下一个时间戳
                currentStamp = getNextStamp();
            }
        } else {
            //不同毫秒，序列号重置
            sequence = 0L;
        }

        lastStamp = currentStamp;//当前时间戳存档，用于判断下次产生id时间戳是否相同

        return (currentStamp - START_STAMP) << TIMESTAMP_LEFT
                | dataCenterId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;

    }

    /**
     * 阻塞直至获得下一个时间戳
     *
     * @return
     */
    public static long getNextStamp() {

        long newStamp = getCurrentStamp();
        while (newStamp <= lastStamp) {
            newStamp = getCurrentStamp();
        }

        return newStamp;
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentStamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {

        Set<Long> set = new HashSet<Long>();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            Long id = SnowFlakeUtils.getNextId();
            set.add(id);
        }

        System.out.println("总耗时 : " + (System.currentTimeMillis() - start));
        System.out.println(set.size());
    }
}
