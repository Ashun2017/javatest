package JavaTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试AtomicInteger的用法
 */
public class MyAtomicInteger {

    public static void main(String[] args) {
        Person person = new Person("33", 22);
        person.setAge(33);
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println("begin " + atomicInteger.get());
        int increment = atomicInteger.getAndIncrement();
        System.out.println("end " + increment + " " + atomicInteger.get());
        System.out.print("");
    }

}
