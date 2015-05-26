package com.marvin.demo.marvin.demo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author Eason
 * @since 15-1-13 下午9:37
 */
public class ExecutorDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
    }
}
