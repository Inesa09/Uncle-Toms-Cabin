package com.queue.nosqlDB.entity;

import com.queue.queue.Request;

public class RequestEntity extends Request {

    private int statusId;
    private String creationTime;
    private String completionTime;

    public RequestEntity(){}

    public RequestEntity(Request r){
        super(r.getGuid(), r.getServiceId(), r.getPriority(), r.getTimeLock(), r.getBody());
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }
}
