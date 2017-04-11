package com.battle.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by Bing.Z on 2017/4/5.
 */
public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(2,new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
    static class A implements Runnable{
        @Override
        public void run() {
            System.out.println("3");
        }
    }
}

