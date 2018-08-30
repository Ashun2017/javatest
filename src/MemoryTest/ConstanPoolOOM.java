package MemoryTest;

/**
 * Describe：常量池测试
 * Author：sunqiushun
 * Date：2018-08-09 19:49:05
 */
public class ConstanPoolOOM {


    public static void main(String[] args){

        String str1 = new StringBuilder("jisuanji").append("coo").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
