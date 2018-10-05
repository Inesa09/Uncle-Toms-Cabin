package com.queue.q.LinkedQueuesRealisation;

import com.queue.q.Request;


public class LinkedQueueNode {
    private Request request;
    private LinkedQueueNode nodeNext;
    private LinkedQueueNode nodeLast;

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public LinkedQueueNode getNodeNext() {
        return nodeNext;
    }

    public void setNodeNext(LinkedQueueNode node) {
        this.nodeNext = node;
    }

    public LinkedQueueNode getNodeLast() {
        return nodeLast;
    }

    public void setNodeLast(LinkedQueueNode nodeLast) {
        this.nodeLast = nodeLast;
    }
}

