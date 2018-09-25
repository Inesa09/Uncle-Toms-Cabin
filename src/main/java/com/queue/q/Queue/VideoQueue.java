package com.queue.q.Queue;

import com.queue.q.Request;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

@Component("VideoQueue")
public class VideoQueue implements IQueue {
    private Queue<Request> queue  = new PriorityQueue<>(new Comparator());

    public void setRequest (Request request) {
        queue.add(request);
    }

    public Request getRequest(){
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

