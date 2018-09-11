package com.queue.q.Queue;

import com.queue.q.Request;

public interface IQueue {

    void setRequest (Request request);
    void lock (Lock lock);
    void unlock (Lock lock);
    Request getRequest();
    boolean isEmpty();
}