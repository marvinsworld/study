package com.marvin.demo.marvin;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:一个线程或多个线程等待其他线程运行达到某一目标后进行自己的下一步工作，
 * 而被等待的“其他线程”达到这个目标后继续自己下面的任务
 *
 * @author Eason
 * @since 15-1-2 下午11:12
 */
public class CountDownLatchDemo {

    private static final int PLAY_AMOUNT = 10;

    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(PLAY_AMOUNT);

        ExecutorService exec = Executors.newFixedThreadPool(PLAY_AMOUNT);

        Player[] players = new Player[PLAY_AMOUNT];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i, begin, end);
        }

        for (Player player : players) {
            exec.execute(player);
        }

        System.out.println("比赛开始");
        begin.countDown();//宣布开始

        try {
            end.await();//等待结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("比赛结束");
        }

        //注意：此时main线程已经要结束了，但是exe线程如果不关闭是不会结束的
        exec.shutdown();
    }


    public static class Player implements Runnable {

        @Override
        public void run() {

            try {
                begin.await();//等待裁判发令枪
                System.out.println("player-" + id + "起跑...");

                Thread.sleep((long) (Math.random() * 100));

                System.out.println("player-" + id + "到达终点");

                end.countDown();

                System.out.println("player-" + id + "继续干其他事情");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private int id;
        private CountDownLatch begin;
        private CountDownLatch end;

        public Player(int id, CountDownLatch begin, CountDownLatch end) {
            this.id = id;
            this.begin = begin;
            this.end = end;
        }
    }
}

//        比赛开始
//        player-0起跑...
//        player-1起跑...
//        player-6起跑...
//        player-3起跑...
//        player-2起跑...
//        player-7起跑...
//        player-8起跑...
//        player-5起跑...
//        player-9起跑...
//        player-4起跑...
//        player-3到达终点
//        player-3继续干其他事情
//        player-2到达终点
//        player-2继续干其他事情
//        player-4到达终点
//        player-4继续干其他事情
//        player-8到达终点
//        player-8继续干其他事情
//        player-5到达终点
//        player-5继续干其他事情
//        player-7到达终点
//        player-7继续干其他事情
//        player-9到达终点
//        player-6到达终点
//        player-9继续干其他事情
//        player-6继续干其他事情
//        player-1到达终点
//        player-1继续干其他事情
//        player-0到达终点
//        player-0继续干其他事情
//        比赛结束