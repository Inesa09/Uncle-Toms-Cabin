package com.queue.db.entity;

import com.queue.q.Request;
import java.util.Date;

public class RequestDB extends Request {

    private boolean executed = false;
    private Date creationTime;
    private Date completionTime;

    public RequestDB(){}

    public RequestDB(Request r){
        super(r.getServiceId(), r.getPriority(), r.getTimeLock(), r.getBody());
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
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
