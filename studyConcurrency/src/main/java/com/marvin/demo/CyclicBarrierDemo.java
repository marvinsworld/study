package com.marvin.demo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * CountDownLatch : 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行。
 * CyclicBarrier : N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。
 * 这样应该就清楚一点了，对于CountDownLatch来说，重点是那个“一个线程”, 是它在等待，
 * 而另外那N的线程在把“某个事情”做完之后可以继续等待，可以终止。
 * 而对于CyclicBarrier来说，重点是那N个线程，他们之间任何一个没有完成，所有的线程都必须等待。
 *
 * CountDownLatch 是计数器, 线程完成一个就记一个, 就像 报数一样, 只不过是递减的.
 * 而CyclicBarrier更像一个水闸, 线程执行就想水流, 在水闸处都会堵住, 等到水满(线程到齐)了, 才开始泄流.
 *
 * CountDownLatch和CyclicBarrier本质是一样的,都是在每个Thread 设置 CountDownLatch或CyclicBarrier 的屏障点point，
 * 每个Thread 计入的Condition 中队列， 执行到屏障点point时候，回调 CountDownLatch或CyclicBarrier 的await方法，
 * 在Condition 中队列遍历是否所有的Thread 都已经执行到屏障点point。但是并没有释放Thread ，Thread 一致是运行状态（可以理解成时等待其他Thread全部完成屏障点point）。
 *  区别是：
 * 每个Thread 到达 CountDownLatch的屏障点point 可以调用 countDown（）计数-1，到全部为计数0的时候，全部其他Thread通过，继续执行或结束。
 * 所有到达 CyclicBarrier 的屏障点point 时候，该屏障点point 结束，继续执行。如果不能全部到达（中断、失败或者超时等原因），
 * 设置 BrokenBarrierException引起所有调用await方法的“Thread”全部报错，全部要么全不 (all-or-none) 的破坏模式。
 * reset也是抛出BrokenBarrierException。
 * 简而言之，CyclicBarrier 实现一致性，CountDownLatch简单计数。
 *
 * @author Eason
 * @since 15-1-3 上午10:53
 */
public class CyclicBarrierDemo {

    private static final int PLAY_AMOUNT = 3;

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(PLAY_AMOUNT);

        ExecutorService executor = Executors.newFixedThreadPool(PLAY_AMOUNT);

        for (int i = 1; i <= PLAY_AMOUNT; i++) {
            executor.execute(new Runner(barrier, i + "号选手"));
        }
        barrier.reset();

        executor.shutdown();
    }


    public static class Runner implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(1500));
                System.out.println(name + " 准备好了...");
                System.out.println(barrier.getNumberWaiting() + "  " + barrier.getParties());
                // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + " 起跑！");
        }

        private String name;
        // 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)
        private CyclicBarrier barrier;

        public Runner(CyclicBarrier barrier, String name) {
            super();
            this.barrier = barrier;
            this.name = name;
        }
    }
}
