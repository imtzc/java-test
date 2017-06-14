package 并发编程;

import java.util.concurrent.Semaphore;

/**
 * Created by don on 2017/6/6.
 *
 * 有限资源，排队抢占
 * 停车场有三个车位，20辆车公平竞争的排序进入
 *
 */
public class C_SemaphoreTest {

    static class Parking{
        //信号量
        private Semaphore semaphore;

        Parking(int count){
            semaphore = new Semaphore(count, true);
        }

        public void park(){
            try {
                //获取信号量
                semaphore.acquire();
                long time = (long) (Math.random() * 10);
                System.out.println(Thread.currentThread().getName() + "进入停车场，停车" + time + "秒..." );
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "开出停车场...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }


    static class Car extends Thread {
        Parking parking ;

        Car(Parking parking){
            this.parking = parking;
        }

        @Override
        public void run() {
            parking.park();     //进入停车场
        }
    }

    public static void main(String[] args){
        Parking parking = new Parking(3);

        for(int i = 0 ; i < 20 ; i++){
            new Car(parking).start();
        }
    }
}
