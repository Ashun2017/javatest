package MemoryTest;

/**
 * Describe：stack 溢出测试
 * Author：sunqiushun
 * Date：2018-08-09 19:49:05
 */
public class HeapOOM {

    public void nostop(){
        while (true){

        }
    }

    public void stackLeakThread(){
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    nostop();
                }
            }).start();
        }
    }

    public static void main(String[] args){
        HeapOOM heapOOM = new HeapOOM();
        heapOOM.stackLeakThread();
    }
}
