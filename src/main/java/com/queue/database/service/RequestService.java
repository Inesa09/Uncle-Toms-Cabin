package com.queue.database.service;

import com.queue.database.dao.IRequestDAO;
import com.queue.database.entity.RequestEntity;
import com.queue.queue.QueueRepository;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.queue.constants.ServiceID.MOBILE_ID;
import static com.queue.constants.ServiceID.WEB_ID;
import static com.queue.constants.StatusID.*;

@Service
public class RequestService implements IRequestService {

    @Autowired
    IRequestDAO requestDAO;

    @Autowired
    QueueRepository repository;

    @Override
    public void fillQueuesWithUnexecutedRequests() {
        List<RequestEntity> unexecuted = requestDAO.getAllWithTheSameStatus(RECEIVED);
        for(Request request : unexecuted){
            int serviceId = request.getServiceId();
            repository.getQueueByServiceID(serviceId).setRequest(request);
        }
    }

    @Override
    public void fillQueueWithUnsentRequests(){
        List<RequestEntity> unsent = requestDAO.getAllWithTheSameStatus(EXECUTED);
        for(Request request : unsent) {
            repository.getQueueByServiceID(MOBILE_ID).setRequest(request);
            repository.getQueueByServiceID(WEB_ID).setRequest(request);
        }
    }

    @Override
    public synchronized Request save(Request request){
        RequestEntity newRequest = new RequestEntity(request);
        requestDAO.add(newRequest);
        return request;
    }

    @Override
    public void updateToExecuted(String guid) {
        requestDAO.updateCompletionTime(guid);
        updateStatus(guid, EXECUTED);
    }

    @Override
    public void updateToSent(String guid) {
        updateStatus(guid, SENT);
    }

    private void updateStatus(String guid, byte statusId){
        if(requestDAO.isExists(guid))
            requestDAO.updateStatus(guid, statusId);
    }
}
