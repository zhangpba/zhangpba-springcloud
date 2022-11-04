package com.study.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangpba
 * @description 分布式锁
 * <p>
 * zookeeper分布式锁的核心思想：当客户端要获取锁，则创建节点，使用完锁，删除节点。
 * 1.客户端获取锁的时候，在根节点下lock(此节点可以任免)创建一个临时顺序节点
 * 2.然后获取lock下面所有的子节点，客户端获取所有的子节点之后，如果发现自己创建的子节点序号最小，那么就认为该客户端获取到了锁。使用完锁之后，将该节点进行删除。
 * 3.如果发现自己创建的节点并非lock所有的节点最小的，说明自己还没有获取到锁，此时客户端需要找到比自己小的那个节点，同时对其注册事件监听，监听删除事件
 * 4.如果发现比自己小的节点被删除了，则客户端的Watcher会收到相应的通知，此时在判断是否是lock子节点序号最小的，如果是则获取到了锁，如果不是则重复以上步骤获取到比自己小的一个节点。并注册监听。
 * ————————————————
 * 原文链接：https://blog.csdn.net/qq_44017091/article/details/124989055
 * @date 2022/10/29
 */
public class Ticket implements Runnable {

    private int tickets = 10; // 模拟十张票
    private InterProcessMutex lock;

    public Ticket() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString("9.134.236.215:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(retryPolicy).build();
        curatorFramework.start();
        lock = new InterProcessMutex(curatorFramework, "/lock");
    }

    @Override
    public void run() {
        while (true) {
            // 加锁
            try {
                // 获取锁
                lock.acquire(3, TimeUnit.SECONDS);
                // 获得许可
                if (tickets > 0) {
                    System.out.println("或得许可：" + Thread.currentThread() + ":" + tickets);
                    tickets--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
