package JavaLock;


import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义同步器实现
 */
class Mutex implements Lock, Serializable {

    /**
     * 自定义的同步器实现类
     */
    public static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 尝试获取独占资源
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
           // assert acquires = 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 尝试释放独占资源
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
           // assert releases = 1;
            if (getState() == 0) { // 状态为0则抛出异常
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 尝试获取共享资源
         *
         * @param arg
         * @return
         */
        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        /**
         * 尝试释放共享资源
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }


    }

    public final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() {}

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
