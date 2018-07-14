package com.queue.q;

public class Request {
    private byte serviceId;
    private byte priority;
    private int type;
    private Object request;

    public byte getServiceId() {
        return serviceId;
    }

    public byte getPriority() {
        return priority;
    }

    public int getType() {
        return type;
    }

    public Object getRequest() {
        return request;
    }
}
