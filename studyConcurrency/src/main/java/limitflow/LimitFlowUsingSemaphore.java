package limitflow;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Description:限制流量,多余的流量会被拦截
 *
 * @author Marvinsworld
 * @since 2015/5/31 10:49
 */
public class LimitFlowUsingSemaphore {

    Semaphore semaphore = new Semaphore(2);

    public void limitFlow() {

        if (semaphore.getQueueLength() > 2) {
            System.out.println("我被拦截了...");
            return;
        }

        Random random = new Random();
        try {
            semaphore.acquire();
            System.out.println("执行中...");
            Thread.sleep(1000 + (random.nextInt() & 5000));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        final LimitFlowUsingSemaphore limitFlow = new LimitFlowUsingSemaphore();

        for (int i = 0; i < 10; i++) {
            new Thread() {

                public void run() {
                    limitFlow.limitFlow();
                }
            }.start();
        }

    }

}
