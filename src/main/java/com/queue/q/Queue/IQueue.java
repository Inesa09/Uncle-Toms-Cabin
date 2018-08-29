package com.queue.q.Queue;

import com.queue.q.Request;
import java.util.PriorityQueue;
import java.util.Queue;

public interface IQueue {
    Queue<Request> queue  = new PriorityQueue<>(new Comparator()); //public static final

    default void setRequest (Request request) {
        queue.add(request);
    }
    default Request getRequest(){
        return queue.poll();
    }
    default boolean isEmpty() {
        return queue.isEmpty();
    }
}