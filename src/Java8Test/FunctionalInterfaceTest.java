package Java8Test;

/**
 * Describe： 接口类型 拥有自己的default、static方法实现
 *
 * @FunctionalInterface 表明该接口是一个函数式接口，只能拥有 一个抽象方法
 * Author：sunqiushun
 * Date：2018-07-30 09:19:14
 */
@FunctionalInterface
public interface FunctionalInterfaceTest {

    /*非default、static方法不能有实现
     * --否则编译错误--Abstract methods do not specify a body
    void sayHello4CompilerError(){};
    */

    void sayHello();

    // void sayWorld();

    /*default、static方法必须有具体的实现
     * --否则编译错误--This method requires a body instead of a semicolon
    default void studyTarget();
    */
    default void studyTarget() {
        System.out.println("出生");
        System.out.println("\t--> 注入知识");
        System.out.println("\t\t--> 生命消亡");
    }

    //可以拥有多个default方法
    default void studyTarget2() {
        System.out.println("DefaultStaticMethodDemo#【default】studyTarget2 invok.");
    }

    //可以拥有多个static方法
    static void info() {
        System.out.println("DefaultStaticMethodDemo#【static】 info invok.");
    }

    public static void main(String[] args) {
        info();

        FunctionalInterfaceTest interfaceTest = new FunctionalInterfaceTest() {
            //仅仅需要实现抽象方法
            //default、static方法不需要强制自己新实现
            @Override
            public void sayHello() {
                System.out.println("sayHello");
            }

          /*  @Override
            public void sayWorld() {
                System.out.println("sayWorld");
            }*/
        };

        interfaceTest.sayHello();
        //  interfaceTest.sayWorld();
        interfaceTest.studyTarget();
        interfaceTest.studyTarget2();
    }
}
