package com.queue.q;



public class Request {
    private byte serviceId;
    private int priority;
    private int timelock = 0;
    private int idToDataBase;
    private Object request;

    public Request() {
        super();
    }



    public Request(byte serviceId, int priority, int timelock, Object request){
        this.serviceId = serviceId;
        this.priority = priority;
        this.timelock = timelock;
        this.request = request;
    }

    public Request(byte serviceId, int priority, Object request){
        this.serviceId = serviceId;
        this.priority = priority;
        this.request = request;
    }

    public byte getServiceId() {
        return serviceId;
    }

    public int getPriority() {
        return priority;
    }

    public Object getRequest() {
        return request;
    }

    public int getTimelock() {
        return timelock;
    }

    public int getIdToDataBase() {
        return idToDataBase;
    }

    public void setServiceId(byte serviceId) {
        this.serviceId = serviceId;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public void setTimelock(int timelock) {
        this.timelock = timelock;
    }

    public void setIdToDataBase(int idToDataBase) {
        this.idToDataBase = idToDataBase;
    }
}

