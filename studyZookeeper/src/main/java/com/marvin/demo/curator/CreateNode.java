package com.marvin.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Description:
 *
 * @author Eason
 * @since 15-5-26 上午5:56
 */
public class CreateNode {

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
                .forPath("/zk-book/c1", "init".getBytes());
    }
}
