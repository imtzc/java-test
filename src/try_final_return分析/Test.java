package try_final_return分析;

/**
 * Created by don on 2017/6/2.
 */
public class Test {

    public int a() { // 输出 2
        int x = 1;

        try {
            return ++x;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ++x;
        }
        return x;
    }

    public int b() { // 输出 3
        int x = 1;

        try {
            return ++x;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return ++x;
        }
    }

    public int c() { // 输出 3
        int x = 1;

        try {
            return ++x / 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ++x;
        }

        return x;
    }

    public static void main(String[] args) {
        Test t = new Test();
        int y = t.a();
        System.out.println(y);
    }
}
