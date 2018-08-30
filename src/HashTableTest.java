import java.util.*;

/**
 * Describe：
 * Author：sunqiushun
 * Date：2018-08-27 14:32:52
 */
public class HashTableTest {
    public static void main(String args[]) {
        Hashtable<String, Integer> table = new Hashtable<String, Integer>();
        //[1]添加元素
        table.put("zhangsan", 22);
        table.put("lisi", 33);
        table.put("wangwu", 44);
        //[2]toString()方式打印
        System.out.println(table.toString());
        System.out.println("====================================");

        //[3]Iterator遍历方式1--键值对遍历entrySet()
        Iterator<Map.Entry<String, Integer>> iter = table.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iter.next();
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println("entrySet:" + key + " " + value);
        }
        System.out.println("====================================");

        Set<Map.Entry<String, Integer>> entries = table.entrySet();
        System.out.println(entries.toArray().length);

        System.out.println("====================================");
        //[4]Iterator遍历方式2--key键的遍历
        Iterator<String> iterator = table.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            int value = table.get(key);
            System.out.println("keySet:" + key + " " + value);
        }
        System.out.println("====================================");
        //[5]通过Enumeration来遍历Hashtable
        Enumeration<String> enu = table.keys();
        while (enu.hasMoreElements()) {
            System.out.println("Enumeration:" + table.keys() + " " + enu.nextElement());
        }

    }
}
