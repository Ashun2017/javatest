package JavaLock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Describe：synchronizedHashMap源码解析
 * Author：sunqiushun
 * Date：2018-07-27 15:07:26
 */
public class SynchronizedXXX {

    public void test11() {
        Map<Object, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        synchronizedMap.put("key1", "first");
        synchronizedMap.put("key2", "second");
        System.out.println(synchronizedMap.toString());
    }

    public static void test10() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("1", "222");
        hashMap.put("2", "333");
        System.out.println(hashMap.toString());

        Set<Object> keySets = hashMap.keySet();
        System.out.println(keySets.toString());
    }

    public static void main(String[] args) {
        test10();
       /* Person p1= new Person("11",11);
        Person p2 = new Person("11", 11);
        System.out.println(p1 + "  " + p2);
        System.out.println(p1.hashCode() + " " + p2.hashCode());
        System.out.println(p1.equals(p2));*/
    }
}
