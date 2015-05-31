package com.marvinsworld.demo.mq.demo.queue;

import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 *
 * @author Eason
 * @since 15-1-11 下午2:10
 */
public class QueueCache {

    private List<Object> queueCache;
    private int max;

    public QueueCache(List<Object> queueCache, int max) {
        this.queueCache = queueCache;
        this.max = max;
    }

    public QueueCache(){
        this.queueCache = new LinkedList<Object>();
        this.max  = 2000;
    }

    public static void main(String[] args) {
        QueueCache a = new QueueCache();
        System.out.println(a);
    }

    public List<Object> getQueueCache() {
        return queueCache;
    }

    public void setQueueCache(List<Object> queueCache) {
        this.queueCache = queueCache;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
