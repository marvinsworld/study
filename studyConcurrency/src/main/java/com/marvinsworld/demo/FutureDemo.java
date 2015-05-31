package com.marvinsworld.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description:试验Java的Future用法
 * 与Callable组合使用,处理返回结果.
 *
 * @author Eason
 * @since 14-12-25 下午10:12
 */
public class FutureDemo {

    public static class Task implements Callable<String> {

        public String call() throws Exception {
            String tid = String.valueOf(Thread.currentThread().getId());
            System.out.printf("Thread#%s : in call\n", tid);
            return tid;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future<String>> results = new ArrayList<Future<String>>();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++)
            results.add(es.submit(new Task()));

        for (Future<String> res : results)
            System.out.println(res.get());
    }
}
