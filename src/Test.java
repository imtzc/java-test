import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

/**
 * Created by don on 2017/6/5.
 */
public class Test {

    public static void main(String[] args) {

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        try {

            System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse("2017-12-12"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}