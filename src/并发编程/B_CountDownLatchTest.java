package 并发编程;

import java.util.concurrent.CountDownLatch;

/**
 * Created by don on 2017/6/6.
 */
public class B_CountDownLatchTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(20);

    /**
     * Boss线程，等待员工到达开会
     */
    static class BossThread extends Thread{
        @Override
        public void run() {
            System.out.println("Boss在会议室等待，总共有" + countDownLatch.getCount() + "个人开会...");
            try {
                //Boss等待
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("老板说：所有人都已经到齐了，开会吧...");
        }
    }

    /**
     * 秘书 线程，等待员工到达开会
     */
    static class MishuThread extends Thread{
        @Override
        public void run() {
            System.out.println("秘书 在会议室等待，总共有" + countDownLatch.getCount() + "个人开会...");
            try {
                //Boss等待
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("秘书说：所有人都已经到齐了，开会吧...");
        }
    }

    //员工到达会议室
    static class EmpleoyeeThread  extends Thread{
        @Override
        public void run() {

            try {

                System.out.println(Thread.currentThread().getName() + "，到达会议室....");
            } finally {

                //员工到达会议室 count - 1
                countDownLatch.countDown();
            }


//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + "，准备出了纸和笔....");
        }
    }

    public static void main(String[] args){
        //Boss线程启动
        new BossThread().start();
//        new MishuThread().start();

        for(int i = 0 ; i < countDownLatch.getCount() ; i++){
            new EmpleoyeeThread().start();
        }
    }
}
