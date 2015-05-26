package com.marvin.demo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Description:
 *
 * @author Eason
 * @since 15-5-25 下午10:49
 */
public class CreateClient {

    public static void main(String[] args) throws InterruptedException {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.8.3:2181,192.168.8.3:2182,192.168.8.3:2183", policy);

        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
