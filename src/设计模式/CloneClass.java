package 设计模式;

import java.io.*;

/**
 * Created by don on 2017/6/14.
 */
public class CloneClass implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    private String string;
    private Domain domain;

    public CloneClass(String string, Domain domain) {
        this.string = string;
        this.domain = domain;
    }

    /* 浅复制 */
    public CloneClass clone() throws CloneNotSupportedException {
        CloneClass proto = (CloneClass) super.clone();
        return proto;
    }

    /* 深复制 */
    public CloneClass deepClone() throws IOException, ClassNotFoundException {

        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (CloneClass) ois.readObject();
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}

class Domain implements Serializable{

    private int age;

    public Domain(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Test{

    public static void main(String[] args) {
        CloneClass c1 = new CloneClass("zhangsan", new Domain(27));
        try {
            CloneClass c2 = c1.deepClone();

            c1.getDomain().setAge(28);

            System.out.println(c2.getDomain().getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
