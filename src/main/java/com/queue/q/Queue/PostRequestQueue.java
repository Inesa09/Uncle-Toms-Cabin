package com.queue.q.Queue;

import com.queue.q.Request;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component("PostRequestQueue")
public class PostRequestQueue implements IPostQueue {
    private Lock lock = new ReentrantLock();
    private Queue<Request> queue = new LinkedList<>();

    public void setRequest(Request request){
        lock.lock();
        try {
            queue.add(request);
        }
        finally {
            lock.unlock();
        }
    }

    public Request getRequest(){
        lock.lock();
        try {
            return queue.poll();
        }
        finally {
            lock.unlock();
        }
    }

    public Request peekRequest(){
        lock.lock();
        try {
            return queue.peek();
        }
        finally {
            lock.unlock();
        }
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

}
