package com.queue.q.Queue;

import com.queue.q.Request;

import java.util.concurrent.locks.Lock;

public interface IQueue {

    void setRequest (Request request);
    Request getRequest();
    boolean isEmpty();
}