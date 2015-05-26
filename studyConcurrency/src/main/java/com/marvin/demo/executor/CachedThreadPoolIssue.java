package com.marvin.demo.marvin.demo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolIssue {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 1; i<80000; i++)
            es.submit(new task());

    }

}
class task implements Runnable{

    @Override
    public void run() {
    try {
        Thread.sleep(4000);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        
    }
    
}