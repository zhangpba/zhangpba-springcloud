package com.study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangpba
 * @description TODO
 * @date 2022/10/29
 */
@Slf4j
@SpringBootTest
public class LockTest {

    @Test
    public void lockTest() throws InterruptedException {
        Ticket ticket = new Ticket();
        // 1.创建客户端
        Thread thread = new Thread(ticket, "携程");
        Thread.sleep(2000);
        Thread thread1 = new Thread(ticket, "飞猪");
        thread.start();
        thread1.start();
    }
}
