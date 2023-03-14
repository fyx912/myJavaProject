package com.test;


import com.ding.RabbitMQApplication;
import com.ding.model.User;
import com.ding.services.hello.HelloSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest(classes= RabbitMQApplication.class)
public class HelloTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void hello() throws Exception {
        AtomicInteger i = new AtomicInteger();
        new Thread(()->{
            for (;;){
                helloSender.send(i.get());
                i.getAndIncrement();
            }
        }).start();
    }

    @Test
    public void object() throws Exception {
        User user = new User("ding","123456");
        helloSender.send(user);
    }

    @Test
    public void sendCall(){
        for (int i = 0; i < 5; i++) {
            helloSender.sendCall(i);
        }
    }

}
