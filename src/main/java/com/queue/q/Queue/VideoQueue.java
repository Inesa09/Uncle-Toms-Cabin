package com.queue.q.Queue;

import com.queue.q.LinkedQueuesRealisation.LinkedQueue;
import com.queue.q.Request;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component("VideoQueue")
public class VideoQueue implements IQueue {
    private LinkedQueue queue = new LinkedQueue();
    private Lock lock = new ReentrantLock();

    public void setRequest (Request request){
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

