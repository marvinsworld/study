package com.marvinsworld.demo.limitflow.configcenter;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * .
 * Created by zhiqiang.ge on 2015/5/29 09:15.
 */
public class ConfigCenter {

    public CuratorFramework createClient() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.8.3:2181", policy);
        return client;
    }

    public void reg(CuratorFramework client){
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                System.out.println("---"+connectionState.name());
            }
        });
    }

    public NodeCache watch(CuratorFramework client,String path){
        final NodeCache nodeCache = new NodeCache(client,path);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.println("NodeCache changed, data is: " + new String(nodeCache.getCurrentData().getData()));
            }
        });

        return nodeCache;
    }

    public static void main(String[] args) throws Exception {
        ConfigCenter configCenter = new ConfigCenter();

        CuratorFramework client = configCenter.createClient();

        //configCenter.reg(client);
        client.start();

        client.newNamespaceAwareEnsurePath("/create");

        NodeCache nodeCache = configCenter.watch(client, "/create");
        nodeCache.start(true);

        client.setData().forPath("/create", "1111".getBytes());
    }
}
