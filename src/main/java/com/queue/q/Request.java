package com.queue.q;

public class Request {
    private byte serviceId;
    private byte priority;
    private int type;
    private boolean locked = false;
    private int timelocked = 0;
    private Object request;

    public Request() {
        super();
    }

    public Request(byte serviceId, byte priority, int type, boolean locked, Object request){
        this.serviceId = serviceId;
        this.priority = priority;
        this.type = type;
        this.request = request;
        this.locked=locked;
    }
    public Request(byte serviceId, byte priority, int type, boolean locked, int timelocked, Object request){
        this.serviceId = serviceId;
        this.priority = priority;
        this.type = type;
        this.request = request;
        this.locked = locked;
        this.timelocked = timelocked;
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

    public boolean isLocked() { return locked; }

    public int getTimelocked() { return timelocked; }

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

    public void setIslocked(boolean locked) { 
        this.locked = locked; 
    }

    public void setTimelocked(int timelocked) { 
        this.timelocked = timelocked; 
    }
}
