package com.queue.q.Queue;

import com.queue.q.Request;

public interface IQueue {

    void setRequest (Request request);
    Request getRequest();
    boolean isEmpty();
}