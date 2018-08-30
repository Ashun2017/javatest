package JavaLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Describe：
 * Author：sunqiushun
 * Date：2018-07-31 10:17:49
 */
public class CompareAndSetTest {

    static AtomicReference<Per> ar_p1 = new AtomicReference<>();

    public static void main(String[] args) {
        Per p1 = new Per("p1", 11);
        Per p2 = new Per("p2", 22);

        // CompareAndSetMethod(p1, p2);
        GetAndSetMethod(p1, p2);
    }

    private static void GetAndSetMethod(Per p1, Per p2) {
        ar_p1.set(p1);
        Per per_get = ar_p1.getAndSet(p2);
        System.out.println("per_get=" + per_get + " ar_p1.value=" + ar_p1.get());
    }

    private static void CompareAndSetMethod(Per p1, Per p2) {
        ar_p1.set(p1);
        boolean b2 = ar_p1.compareAndSet(p1, p2);
        System.out.println("是否交换成功：" + b2);
        System.out.println("交换之后的p1和p2：p1=" + p1.toString() + " p2=" + p2.toString());
        System.out.println("交换之后的arp1:" + ar_p1.get());

        System.out.println("----------------------------------------------------------------");

        System.out.println(ar_p1.get());
        boolean b3 = ar_p1.compareAndSet(p2, null);
        System.out.println("是否交换成功：" + b3);
        System.out.println("交换之后的arp1:" + ar_p1.get());
    }
}

class Per {

    public String name;
    public int age;

    public Per(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Per{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
