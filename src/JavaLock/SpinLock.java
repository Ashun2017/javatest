package JavaLock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    public AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread currentThread = Thread.currentThread();

        // 如果锁未被占用，则设置当前线程为锁的拥有者
        while (owner.compareAndSet(null, currentThread)) {
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        // 锁的拥有者释放锁
        owner.compareAndSet(currentThread, null);
    }
}
