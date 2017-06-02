package 字符串常量池分析;

import java.lang.reflect.Field;

public class StringPool {

    public static void main(String[] args) throws Exception {

                String a = "tangzc";
                String b = "tangzc";
                String c = new String("tangzc");
                System.out.println("--------------修改前值-------------------");
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("c = " + c);

                //修改String的值
                Field a_ = String.class.getDeclaredField("value");
                a_.setAccessible(true);
                char[] value=(char[])a_.get(a);
                value[4]='_';   //修改a所指向的值

                System.out.println("--------------修改后值-------------------");
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("tangzc");
                System.out.println("c = " + c);
    }
}
