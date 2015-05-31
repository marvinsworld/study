package com.marvinsworld.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Eason
 * @since 15-5-26 上午5:56
 */
public class ReadNode {


    static String path = "/zk-book/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.8.3:2181,192.168.8.3:2182,192.168.8.3:2183")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "1".getBytes());

        Stat stat = new Stat();
        System.out.println("----------"+Arrays.toString(client.getData().storingStatIn(stat).forPath(path)));
        System.out.println("----------"+stat);
    }
}
