package com.queue.db.entity;

import com.queue.q.Request;
import java.util.Date;

public class RequestDB extends Request {

    private int status;
    private Date creationTime;
    private Date completionTime;

    public RequestDB(){}

    public RequestDB(Request r){
        super(r.getServiceId(), r.getPriority(), r.getTimeLock(), r.getBody());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }
}
