package com.marvinsworld.demo;

import java.util.Random;

/**
 * .
 * Created by zhiqiang.ge on 2015/5/31 16:36.
 */
public class BtraceDemo {
    public static void main(String[] args) {
        //CaseObject object = new CaseObject();
        while (true) {
            Random random = new Random();
            execute(random.nextInt(4000));
            //object.execute(random.nextInt(4000));
        }
    }

    public static Integer execute(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("sleep time is=>" + sleepTime);
        return 0;
    }
}
