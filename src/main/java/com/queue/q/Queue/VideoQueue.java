package com.queue.q.Queue;

import com.queue.q.Request;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component("VideoQueue")
public class VideoQueue implements IQueue {
    private Queue<Request> queue  = new PriorityQueue<>(new Comparator());

    public void setRequest (Request request){
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            queue.add(request);
        }
        finally {
            lock.unlock();
        }
    }

    public Request getRequest(){

        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            return queue.poll();
        }
        finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

