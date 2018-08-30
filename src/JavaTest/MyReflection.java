package JavaTest;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 测试 Reflection
 */
public class MyReflection {
    public static void main(String[] args) {
        Test232 test232 = new Test232();
        test232.h();
        LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<>();
        HashMap hashMap = new HashMap();
    }
}

class Test232 {
    public void h() {
        Test231 test231 = new Test231();
        test231.g();
    }
}

class Test231 {
    public void g() {
        gg();
    }

    public void gg() {
        // System.out.println(Reflection.getCallerClass(-1));
        System.out.println(sun.reflect.Reflection.getCallerClass(0));

        System.out.println(sun.reflect.Reflection.getCallerClass(1));
        System.out.println(sun.reflect.Reflection.getCallerClass(2));
        System.out.println(sun.reflect.Reflection.getCallerClass(3));
        System.out.println(sun.reflect.Reflection.getCallerClass(4));
        System.out.println(sun.reflect.Reflection.getCallerClass(5));
    }
}
