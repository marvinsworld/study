package com.marvinsworld.demo.limitflow.syn;

/**
 * Description:
 *
 * @author Eason
 * @since 15-2-7 下午2:02
 */
public class SynchronizedTest {

    public synchronized void test1() {
        System.out.println("test1开始");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1结束");
    }

    public synchronized void test2() {

        System.out.println("test2开始");
    }


    public static void main(String[] args) {

        final SynchronizedTest test = new SynchronizedTest();
        new Thread() {
            public void run() {
                test.test1();
                System.out.println("线程1开始");
            }
        }.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread() {
            public void run() {
                test.test2();
                System.out.println("线程2开始");
            }
        }.start();
    }
}
