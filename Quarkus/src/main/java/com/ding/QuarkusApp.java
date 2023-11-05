package com.ding;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * <p> Quarkus启动类 </p>
 * 启动类说明:
 * 	1.@QuarkusMain注解就是标注了这个类为启动类，项目启动时会直接来运行这个类,打包的时候也会将这个类作为启动类。
 * 	2.启动类实现了QuarkusApplication接口，需要重写run方法。
 * @author Tintin
 * @date 2023-11-05 22:14:15
 **/
@QuarkusMain
public class QuarkusApp implements QuarkusApplication {
    public static void main(String[] args) {
        Quarkus.run(QuarkusApp.class,args);
    }

    @Override
    public int run(String... args) throws Exception {
        Quarkus.waitForExit();
        return 0;
    }
}