package com.queue.q.Queue;

import com.queue.q.Request;

public class Comparator implements java.util.Comparator<Request> {
    public int compare(Request req1, Request req2){
        if(req1.getPriority() > req2.getPriority()){
            return -1;
        } else if(req1.getPriority() < req2.getPriority()){
            return 1;
        } else
            return 0;
    }
}
