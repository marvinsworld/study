package com.marvinsworld.demo.configcenter;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 配置中心多客户端监听.
 * Created by zhiqiang.ge on 2015/6/4 21:17.
 */
public class ConfigCenterWithMultipleClient {

    static String PATH = "/create";

    /**
     * 创建客户端
     */
    public CuratorFramework createClient() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.8.3:2181", policy);
        return client;
    }

    /**
     * 设置客户端监听指定路径
     */
    public NodeCache watch(CuratorFramework client, String path) {
        final NodeCache nodeCache = new NodeCache(client, path);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.println("-----------NodeCache changed, data is: " + new String(nodeCache.getCurrentData().getData()));
            }
        });

        return nodeCache;
    }

    public static void main(String[] args) throws Exception {
        final ConfigCenterWithMultipleClient configCenter = new ConfigCenterWithMultipleClient();


        //启动三个线程 分别起三个客户端监听指定节点
        for(int i=0;i<3;i++){
            new Thread(){
                public void run(){
                    CuratorFramework client = configCenter.createClient();
                    client.start();
                    client.newNamespaceAwareEnsurePath(PATH);

                    NodeCache nodeCache = configCenter.watch(client, PATH);
                    try {
                        nodeCache.start(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        CuratorFramework client = configCenter.createClient();
        client.start();
        client.newNamespaceAwareEnsurePath(PATH);
        NodeCache nodeCache = configCenter.watch(client, PATH);
        nodeCache.start(true);

        client.setData().forPath(PATH, "222".getBytes());
        System.out.println("-------------------change");

        try {
            Thread.sleep(1000*60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
