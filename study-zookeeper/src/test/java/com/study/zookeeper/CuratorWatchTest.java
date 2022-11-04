package com.study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangpba
 * @description TODO
 * @date 2022/10/29
 */
@Slf4j
@SpringBootTest
public class CuratorWatchTest {

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
     * NodeCache:指定单一节点注册监听
     */
    @Test
    public void testNodeCache() throws Exception {
        // 1.创建Nodecache对象
        final NodeCache nodeCache = new NodeCache(curatorFramework, "/test");

        // 2.注册监听
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("节点变化了");
                // 获取修改节点的数据
                byte[] data = nodeCache.getCurrentData().getData();
                System.out.println("获取修改节点的数据:" + new String(data));
            }
        });

        // 3.开启监听,如果设置为true 则开启监听
        nodeCache.start(true);
        while (true) {

        }
    }

    /**
     * pathChildrenCache:监听某个节点的所有子节点
     */
    @Test
    public void testNodeCache1() throws Exception {
        // 1.创建监听对象
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/test", true);

        //  2.绑定监听器
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) {
                System.out.println("子节点发生变化了:" + pathChildrenCacheEvent);
            }
        });

        // 3.开启监听
        pathChildrenCache.start();

        while (true) {

        }
    }

}
