package com.marvinsworld.demo.limitflow;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

class Master implements Watcher {

    ZooKeeper zk;
    String hostPort;

    private static CountDownLatch sem = new CountDownLatch(1);

    Master(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 5000, this);
    }

    public void process(WatchedEvent e) {
        System.out.println("Receive event:" + e);
        if (Event.KeeperState.SyncConnected == e.getState()) {
            sem.countDown();
        }
    }

    String serverId = Integer.toHexString(new Random().nextInt());


    boolean isLeader = false;

    // returns true if thereis a master
    boolean checkMaster() throws KeeperException, InterruptedException {
        while (true) {
            try {
                Stat stat = new Stat();
                byte data[] = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (KeeperException.NoNodeException e) {
                // no master, so trycreate again
                return false;
            } catch (KeeperException.ConnectionLossException e) {
            }
        }
    }

    void runForMaster() throws InterruptedException, KeeperException {
        while (true) {
            try {
                zk.create("/master", serverId.getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (KeeperException.NodeExistsException e) {
                isLeader = false;
                break;
            } catch (KeeperException e) {
                e.printStackTrace();
            }
            if (checkMaster()) break;
        }
    }

    void stopZK() throws Exception { zk.close();}


    public static void main(String args[]) throws Exception {
        Master m = new Master("192.168.8.3:2181");
        m.startZK();
        System.out.println("begin:" + m.zk.getState());

        m.runForMaster();
        if (m.isLeader) {
            System.out.println("I'm the leader");
            // wait for a bit
            Thread.sleep(60000);
        } else {
            System.out.println("Someone else is the leader");
        }
        m.stopZK();


//        try {
//            sem.await();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        System.out.println("end:" + m.zk.getState());
//        System.out.println("zk session establieshed.....");
//
//        // wait for a bit
//        Thread.sleep(60000);
    }
}