package com.queue.q;

public class Comparator implements java.util.Comparator<Request> {
    public int compare(Request req1, Request req2){
        if(req1.getPriority()>req2.getPriority()){
            return -1;
        } if(req1.getPriority()<req2.getPriority()){
            return 1;
        } else
            return 0;
    }
}
