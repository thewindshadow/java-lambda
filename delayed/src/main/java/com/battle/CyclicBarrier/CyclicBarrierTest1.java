package com.battle.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * Created by Bing.Z on 2017/4/7.
 */
public class CyclicBarrierTest1 {

    public static CyclicBarrier getCyclicBarrier(int count) {
        if (count <= 0)
            return null;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(count,
                new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("conditon is arrive and CycleBarrier is running");
                    }
                });
        return cyclicBarrier;
    }
    public static Thread getThread(String nameOfThread,final CyclicBarrier cyclicBarrier) {
        Thread thread = new Thread(nameOfThread) {
            public void run() {
                System.out.println(this.getName() +
                        "is begin; and count is "+ (++count));
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + "finished");
            }
        };
        return thread;
    }
    static int count = 0;
    public static void main(String[] args) {
        /** define a cyclicBarrier and number of barrier is 2. */
        CyclicBarrier cyclicBarrier = getCyclicBarrier(2);
        Thread threadOne = getThread("threadOne", cyclicBarrier);
        threadOne.start();
        Thread threadTwo = getThread("threadTwo", cyclicBarrier);
        threadTwo.start();
    }
}
