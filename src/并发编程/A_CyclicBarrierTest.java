package 并发编程;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by don on 2017/6/6.
 *
 * 某一个线程，拦截指定数量的线程await，数量达标后，放行所有线程
 *
 */
public class A_CyclicBarrierTest {

    static class CyclicBarrierThread extends Thread{

        private CyclicBarrier cyclicBarrier;

        public CyclicBarrierThread(CyclicBarrier cyclicBarrier) {

            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + "到了");
            //等待
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "系好了");
        }
    }

    public static void main(String[] args){

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName()+"人都到齐了，开车吧....都系好安全带了吗？");
            }
        });

        System.out.println("车辆等待中...");
        for(int i = 0 ; i < 5 ; i++){
            new CyclicBarrierThread(cyclicBarrier).start();
        }
    }
}
