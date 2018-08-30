import sun.nio.ch.Net;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Describe：
 * Author：sunqiushun
 * Date：2018-08-27 15:12:58
 */
public class HashMapTest {
    public static void main(String[] args){
        Map<String, Integer> hm = new HashMap<>();
        hm.put("a", 1);
        hm.put("b", 2);
        hm.put("c", 3);

        Set<String> strings = hm.keySet();
        for (String key: strings) {
            Integer integer = hm.get(key);
            System.out.println("key:" + key + " value:" + integer);
        }
        System.out.println("---------------------------------------------------");
        Iterator<String> iterator = hm.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Integer value = hm.get(key);
            System.out.println("key:" + key + " value:" + value);
        }
        System.out.println("==================================================");
        Set<Map.Entry<String, Integer>> entries = hm.entrySet();
        for (Map.Entry<String, Integer> entry:entries) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("key:" + key + " value:" + value);
        }
        System.out.println("--------------------------------------------------");
        Iterator<Map.Entry<String, Integer>> iter = hm.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<String, Integer> next = iter.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println("key:" +key+ " value:" + value);
        }
    }
}
