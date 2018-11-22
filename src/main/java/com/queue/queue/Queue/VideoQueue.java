package com.queue.queue.Queue;

import com.queue.queue.LinkedQueuesRealisation.LinkedQueue;
import com.queue.queue.Request;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component("VideoQueue")
public class VideoQueue implements IQueue {
    private LinkedQueue queue = new LinkedQueue();
    private Lock lock = new ReentrantLock();

    public void setQueue(LinkedQueue queue){
        this.queue = queue;
    }

    public void setRequest (Request request){
        lock.lock();
        try {
            if(request.getDeleteTime() == 0) {
                queue.setRequest(request);
            }
            else {
                queue.setRequestWithTimeLimit(request);
            }
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

