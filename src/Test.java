import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by don on 2017/6/5.
 */
public class Test {

    static {
        System.out.print("2");
    }

    private static Test test = new Test();

    {
        System.out.print("3");
    }

    public static void main(String[] args) {

        System.out.print("1");
        new Test();
    }
}

class TestClassLoad {

//    public static void main(String[] args) {
//
//        System.out.println("--------------");
//        new Test();
//    }
}
