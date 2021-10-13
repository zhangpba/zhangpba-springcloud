package com.study.zookeeper.producer.service;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * zk注册类
 *
 * @author zhangpba
 * @date 2021-10-13
 */
public class Register {

    private static final String SERVER_PATH = "/product";

    private static final String ZK_ADDRESS = "127.0.0.1:2181";

    private static final int ZK_TIMEOUT = 20000;

    /**
     * 注册
     *
     * @param address 地址
     */
    public void register(String address) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZK_ADDRESS, ZK_TIMEOUT, WatchEvent -> {
            });
            Stat stat = zooKeeper.exists(SERVER_PATH, false);
            if (stat == null) {
                zooKeeper.create(SERVER_PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            String path = address;
            //创建短暂的可排序的子节点
            zooKeeper.create(SERVER_PATH + "/instance", path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
