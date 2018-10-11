package com.queue.q;

public class Request {
    private int requestDBId;
    private byte serviceId;
    private byte priority;
    private int timeLock = 0;
    private Object body;

    public Request() {
        super();
    }

    public Request(byte serviceId, byte priority, int timeLock, Object body){
        this.serviceId = serviceId;
        this.priority = priority;
        this.timeLock = timeLock;
        this.body = body;
    }

    public Request(byte serviceId, byte priority, Object body){
        this.serviceId = serviceId;
        this.priority = priority;
        this.body = body;
    }

    public int getRequestDBId() {
        return requestDBId;
    }

    public void setRequestDBId(int requestDBId) {
        this.requestDBId = requestDBId;
    }

    public byte getServiceId() {
        return serviceId;
    }

    public void setServiceId(byte serviceId) {
        this.serviceId = serviceId;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public int getTimeLock() {
        return timeLock;
    }

    public void setTimeLock(int timeLock) {
        this.timeLock = timeLock;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}

