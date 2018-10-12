package com.queue.db.service;

import com.queue.db.dao.IRequestDAO;
import com.queue.db.entity.RequestDB;
import com.queue.q.LinkedQueuesRealisation.LinkedQueue;
import com.queue.q.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService implements IRequestService {

    @Autowired
    private IRequestDAO requestDAO;

    @Override
    public List<Request> getAll() {
        return requestDAO.getAll();
    }

    @Override
    public LinkedQueue[] getQueuesWithUnexecutedRequests() {
        List<Request> unexecuted = requestDAO.getAllUnexecuted();
        LinkedQueue[] queues = new LinkedQueue[] {new LinkedQueue(), new LinkedQueue()};
        for(Request request : unexecuted){
            int serviceId = request.getServiceId();
            int queuePosition = serviceId-1;
            queues[queuePosition].setRequest(request);
        }
        return queues;
    }

    @Override
    public synchronized Request saveAndSetId(Request request){
        RequestDB newRequest = new RequestDB(request);
        requestDAO.add(newRequest);
        int requestDBId = requestDAO.getLastId();
        request.setRequestDBId(requestDBId);
        return request;
    }

    @Override
    public void updateToExecuted(int requestId) {
        if(requestDAO.isExists(requestId))
            requestDAO.updateToExecuted(requestId);
    }
}
