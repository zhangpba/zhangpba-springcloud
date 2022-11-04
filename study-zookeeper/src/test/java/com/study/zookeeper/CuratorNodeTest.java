package com.study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zhangpba
 * @description 测试类
 * @date 2022/10/28
 */
@Slf4j
@SpringBootTest
public class CuratorNodeTest {

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
     * 增加持久化节点
     */
    @Test
    public void createNode() throws Exception {
        // 增加持久化节点
        String path = curatorFramework.create().forPath("/test1");
        System.out.println("增加持久化节点：" + path);
    }

    /**
     * 创建节点并赋值
     *
     * @throws Exception
     */
    @Test
    public void crearteAndSetData() throws Exception {
        String path = curatorFramework.create().forPath("/test2", "zhangsan".getBytes(StandardCharsets.UTF_8));
        System.out.println("创建节点并赋值：" + path);
    }

    /**
     * 查询数据 get
     * 查询节点 ls
     * 查询节点信息 ls-s
     *
     * @throws Exception
     */
    @Test
    public void getData() throws Exception {
        byte[] result = curatorFramework.getData().forPath("/test2");
        System.out.println("创建节点并赋值：" + new String(result));
    }


    /**
     * 查询子节点
     *
     * @throws Exception
     */
    @Test
    public void getChildren() throws Exception {
        List<String> strings = curatorFramework.getChildren().forPath("/test2");
        System.out.println("查询子节点：" + strings);
    }

    /**
     * 查询节点状态
     *
     * @throws Exception
     */
    @Test
    public void storingStatIn() throws Exception {
        Stat stat = new Stat();
        curatorFramework.getData().storingStatIn(stat).forPath("/test2");
        System.out.println("查询节点状态：" + stat);
    }

    /**
     * 修改节点
     */
    @Test
    public void setData() throws Exception {
        // 给持久化节点赋值
        Stat stat = curatorFramework.setData().forPath("/test2", "zhangsanlisi".getBytes(StandardCharsets.UTF_8));
        System.out.println("增加持久化节点：" + stat);
    }

    /**
     * 根据版本修改
     *
     * @throws Exception
     */
    @Test
    public void updateByVersion() throws Exception {
        Stat stat = new Stat();
        curatorFramework.getData().storingStatIn(stat).forPath("/test2");
        int version = stat.getVersion();
        System.out.println("版本号：" + version);
        Stat stat1 = curatorFramework.setData().withVersion(version).forPath("/test2", "yuanshujuxiugai".getBytes());
        System.out.println("修改后的元数据：" + stat1);
    }

    /**
     * 删除节点
     *
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {
        String path = "/test1";
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
    }
}
