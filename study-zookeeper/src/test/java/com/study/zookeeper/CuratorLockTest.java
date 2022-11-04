package com.study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangpba
 * @description 读写锁
 * 读锁（读锁共享）：大家都可以读。上锁前提：之前的锁没有写锁
 * 写锁（写锁排他）：只有得到写锁的才能写。上锁前提：之前没有任何锁
 * @date 2022/10/31
 */
@Slf4j
@SpringBootTest
public class CuratorLockTest {

    private CuratorFramework curatorFramework;

    /**
     * 测试方法执行之前执行：初始化客户端
     */
    @Before
    public void init() {
        /*
        String connectString  // 连接字符串
        int sessionTimeoutMs  // 会话超时时间
        int connectionTimeoutMs  // 连接超时时间
        RetryPolicy retryPolicy   // 重试策略
        * */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);

        curatorFramework = CuratorFrameworkFactory.newClient(
                ConfigInfo.hosts,
                60 * 1000,
                15 * 1000, retryPolicy);

        // 开启连接
        System.out.println("启动客户端连接...");
        curatorFramework.start();
    }

    /**
     * 测试方法执行之后执行：关闭客户端连接
     */
    @After
    public void close() {
        if (curatorFramework != null) {
            System.out.println("关闭客户端连接...");
            curatorFramework.close();
        }
    }

    /**
     * 获取读锁
     */
    @Test
    public void getReadLock() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock");
        // 获取读锁对象
        InterProcessLock interProcessLock = interProcessReadWriteLock.readLock();
        log.info("等待获取读锁对象中...");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(3000);
            log.info("等待: {}", i);
        }
        // 释放锁
        interProcessLock.release();
        log.info("等待释放锁...");
    }

    /**
     * 获取读锁
     */
    @Test
    public void getReadLock1() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock");
        // 获取读锁对象
        InterProcessLock interProcessLock = interProcessReadWriteLock.readLock();
        log.info("等待获取读锁对象中...");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(3000);
            log.info("等待: {}", i);
        }
        // 释放锁
        interProcessLock.release();
        log.info("等待释放锁...");
    }

    /**
     * 写锁
     *
     * @throws Exception
     */
    @Test
    public void getWriteLock() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock");
        // 获取读锁对象
        InterProcessLock interProcessLock = interProcessReadWriteLock.writeLock();
        log.info("等待获取写锁对象中...");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(3000);
            log.info("等待: {}", i);
        }
        // 释放锁
        interProcessLock.release();
        log.info("等待释放锁...");
    }

    /**
     * 写锁
     *
     * @throws Exception
     */
    @Test
    public void getWriteLock1() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock");
        // 获取读锁对象
        InterProcessLock interProcessLock = interProcessReadWriteLock.writeLock();
        log.info("等待获取写锁对象中...");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(3000);
            log.info("等待: {}", i);
        }
        // 释放锁
        interProcessLock.release();
        log.info("等待释放锁...");
    }
}
