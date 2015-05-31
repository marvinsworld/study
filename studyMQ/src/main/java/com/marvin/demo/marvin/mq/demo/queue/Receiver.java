package com.marvinsworld.demo.marvin.mq.demo.queue;

import java.util.List;

/**
 * Description:
 *
 * @author Eason
 * @since 15-1-11 下午2:34
 */
public class Receiver {

    private QueueCache queueCache;

    public Receiver(QueueCache queueCache) {
        this.queueCache = queueCache;
    }

    public void receive() {
        final List<Object> queue = queueCache.getQueueCache();

        new Thread() {
            public void run() {
                if (queue.size() > 0) {
                    Object result = queue.remove(0);
                    System.out.println(result + "receive");
                }
            }

        }.start();
    }
}
