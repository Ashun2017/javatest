package JavaLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Describe：
 * Author：sunqiushun
 * Date：2018-07-31 13:46:33
 */
public class MCSLock implements Lock {
    private AtomicReference<QNode> tail;
    private ThreadLocal<QNode> myNode;

    public MCSLock() {
        tail = new AtomicReference<QNode>(null);
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
    }

    public void lock() {
        QNode qnode = myNode.get();
        QNode pred = tail.getAndSet(qnode);
        if (null != pred) {
            qnode.locked = true;
            pred.next = qnode;
        }
        while (qnode.locked) {
        }
    }

    public void unlock() {
        QNode qnode = myNode.get();
        if (null == qnode.next) {
            if (tail.compareAndSet(qnode, null)) {
                return;
            }
            while (null == qnode.next) {
            }
        }
        qnode.next.locked = false;
        qnode.next = null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

class QNode {
    public volatile boolean locked = false;
    public QNode next = null;
}