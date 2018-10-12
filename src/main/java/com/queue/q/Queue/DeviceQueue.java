package com.queue.q.Queue;

import com.queue.q.LinkedQueuesRealisation.LinkedQueue;
import com.queue.q.Request;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component("DeviceQueue")
public class DeviceQueue implements IServiceQueue {
    private LinkedQueue queue = new LinkedQueue();
    private Lock lock = new ReentrantLock();

    public void setQueue(LinkedQueue queue){
        this.queue = queue;
    }

    public void setRequest (Request request) {
        lock.lock();
        try {
            queue.setRequest(request);
        }
        finally {
            lock.unlock();
        }
    }

    public Request getRequest(){
        lock.lock();
        try {
            return queue.getRequest();
        }
        finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

