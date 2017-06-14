import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by don on 2017/6/5.
 */
public class Test {

    public static void main(String[] args) {

        ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

        ReentrantLock rl = new ReentrantLock();
    }
}
