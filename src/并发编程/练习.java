package 并发编程;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by don on 2017/6/6.
 * <p>
 * 问题：一个池塘，有很多鸟和很多鱼，
 * 鸟每60秒产生1个后代，
 * 鱼每30秒产生2个后代。
 * 鸟每10秒吃掉1条鱼。
 * 建一个池塘，初始化一些鱼和鸟，看看什么时候鸟把鱼吃光。
 */
public class 练习 {

    static AtomicInteger yu = new AtomicInteger(10);
    static AtomicInteger niao = new AtomicInteger(2);

    static volatile boolean shiJieHuiMie = true;

    public static void main(String[] args) {

        new MingYun().start();
        new Yu().start();
        new Niao().start();
        new NiaoChiYu().start();
    }

    public static class Yu extends Thread {

        @Override
        public void run() {

            for (long time = 0; 练习.shiJieHuiMie; time++) {

                long start = System.currentTimeMillis();

                if (time > 0 && time % 30 == 0) {

                    int add = 2 * 练习.yu.get();
                    System.out.println("增加了" + add + "条鱼："+练习.yu.addAndGet(add));
                }

                try {
                    if(练习.shiJieHuiMie)
                        Thread.sleep(1000 - (System.currentTimeMillis() - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class NiaoChiYu extends Thread {

        @Override
        public void run() {

            for (long time = 0; 练习.shiJieHuiMie; time++) {

                long start = System.currentTimeMillis();

                if (time > 0 && time % 10 == 0) {

                    int add = 练习.niao.get();
                    System.out.println("被吃了" + add + "条鱼："+练习.yu.addAndGet(-add));
                }

                try {
                    if(练习.shiJieHuiMie)
                        Thread.sleep(1000 - (System.currentTimeMillis() - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Niao extends Thread {

        @Override
        public void run() {

            for (long time = 0; 练习.shiJieHuiMie; time++) {

                long start = System.currentTimeMillis();

                if (time > 0 && time % 60 == 0) {

                    int add = 练习.niao.get();
                    System.out.println("增加了" + add + "只鸟："+ 练习.niao.addAndGet(add));
                }

                try {
                    if(练习.shiJieHuiMie)
                        Thread.sleep(1000 - (System.currentTimeMillis() - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class MingYun extends Thread {

        @Override
        public void run() {

            for (long time = 0; 练习.shiJieHuiMie; time++) {

                long start = System.currentTimeMillis();

                if (练习.yu.get() <= 0) {
                    练习.shiJieHuiMie = false;
                    System.out.println("-----------用时：" + time + "秒");
                }else{

                    System.out.println("鸟("+练习.niao.get()+")鱼("+练习.yu.get()+")和平");
                }

                try {
                    if(练习.shiJieHuiMie)
                        Thread.sleep(1000 - (System.currentTimeMillis() - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
