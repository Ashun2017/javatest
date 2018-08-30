package JavaTest;

/**
 * 测试类的普通引用
 */
public class CommonReference {

    public static Person person;

    public static void main(String[] args) throws InterruptedException {
        // 普通引用
        person = new Person("Tom", 18);
        System.out.println("Person is " + person.toString());
        Thread t1 = new Thread(new Task1());
        Thread t2 = new Thread(new Task2());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Now Person is " + person.toString());
    }

    static class Task1 implements Runnable {
        public void run() {
            person.setAge(person.getAge() + 1);
            person.setName("Tom1");

            System.out.println("Thread1 Values "
                    + person.toString());
        }
    }

    static class Task2 implements Runnable {
        public void run() {
            person.setAge(person.getAge() + 2);
            person.setName("Tom2");

            System.out.println("Thread2 Values "
                    + person.toString());
        }
    }


    public void test3() {
        System.out.print("ddd");
    }

}
