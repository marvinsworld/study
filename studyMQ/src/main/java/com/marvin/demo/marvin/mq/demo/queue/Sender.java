package com.marvinsworld.demo.marvin.mq.demo.queue;

import java.util.List;

/**
 * Description:
 *
 * @author Eason
 * @since 15-1-11 下午1:52
 */
public class Sender {

    private QueueCache queueCache;

    public Sender(QueueCache queueCache) {
        this.queueCache = queueCache;
    }

    public void sender(final String msg) {
        final List<Object> queue = queueCache.getQueueCache();

        new Thread() {
            public void run() {
                int size = queue.size();
                if (size >= queueCache.getMax()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    queue.add(msg);
                    System.out.println(msg+"send...");
                }
            }
        }.start();
    }

}
