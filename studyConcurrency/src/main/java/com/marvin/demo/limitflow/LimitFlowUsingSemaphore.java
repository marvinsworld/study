package com.marvin.demo.limitflow;

import java.util.concurrent.Semaphore;

/**
 * Description:限制流量
 *
 * @author Marvinsworld
 * @since 2015/5/31 10:49
 */
public class LimitFlowUsingSemaphore {

    Semaphore semaphore = new Semaphore(2);

    public void limitFlow() {

        if (semaphore.getQueueLength() > 0) {
            return;
        }

        try {
            semaphore.acquire();
            System.out.println("执行中...");
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        final LimitFlowUsingSemaphore limitFlow = new LimitFlowUsingSemaphore();

        for (int i = 0; i < 3; i++) {
            new Thread() {

                public void run() {
                    limitFlow.limitFlow();
                }
            }.start();
        }

    }

}
