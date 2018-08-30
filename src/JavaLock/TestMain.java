package JavaLock;

/**
 * Describe：接口测试
 * Author：sunqiushun
 * Date：2018-07-27 16:33:44
 */
interface InterfaceTest<K, V> {
    void test11();

    void test2();

    default V test3(K k, V v) {
        V newV = null;
        if (k != v) {
            return v;
        }
        return newV;
    }
}

class TestInterfaceImpl implements InterfaceTest {
    @Override
    public void test11() {

    }

    @Override
    public void test2() {

    }
}

public class TestMain {
    public static void main(String[] args) {
        TestInterfaceImpl test = new TestInterfaceImpl();
        test.test2();
        test.test11();
        System.out.println(test.test3(2, 3));

        String obj = null;
        String obj2 = "22";
        System.out.println(obj == obj2);
        // System.out.println(obj.equals(obj2));
    }
}
