package com.queue.q;

public class Request {
    private byte serviceId;
    private byte priority;
    private int type;
    private Object request;

    public Request(byte serviceId, byte priority, int type, Object request){
        this.serviceId = serviceId;
        this.priority = priority;
        this.type = type;
        this.request = request;
    }

    public Request(byte serviceId, byte priority, Object request){
        this.serviceId = serviceId;
        this.priority = priority;
        this.request = request;
    }

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

    public void setServiceId(byte serviceId) {
        this.serviceId = serviceId;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }
    public void setType(int type) {
        this.type = type;
    }

    public void setRequest(Object request) {
        this.request = request;
    }
}
