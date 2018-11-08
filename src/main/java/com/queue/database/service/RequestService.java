package com.queue.database.service;

import com.queue.constants.ServiceID;
import com.queue.database.dao.IRequestDAO;
import com.queue.database.entity.RequestDB;
import com.queue.queue.Queue.DeviceQueue;
import com.queue.queue.Queue.PostRequestQueue;
import com.queue.queue.Queue.VideoQueue;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService implements IRequestService {

    @Autowired
    private IRequestDAO requestDAO;

    @Autowired
    private DeviceQueue deviceQueue;
    @Autowired
    private VideoQueue videoQueue;
    @Autowired
    private PostRequestQueue postRequestQueue;

    @Override
    public List<RequestDB> getAll() {
        return requestDAO.getAll();
    }

    @Override
    public void fillQueuesWithUnexecutedRequests() {
        List<RequestDB> unexecuted = requestDAO.getAllUnexecuted();
        for(Request request : unexecuted){
            int serviceId = request.getServiceId();
            switch (serviceId) {
                case (ServiceID.DEVICE_ID):
                    deviceQueue.setRequest(request);
                    break;
                case (ServiceID.VIDEO_ID):
                    videoQueue.setRequest(request);
                    break;
            }
        }
    }

    @Override
    public void fillQueueWithUnsentRequests(){
        List<RequestDB> unsent = requestDAO.getAllUnsent();
        for(Request request : unsent)
            postRequestQueue.setRequest(request);
    }

    @Override
    public synchronized Request saveAndSetId(Request request){
        RequestDB newRequest = new RequestDB(request);
        requestDAO.add(newRequest);
        int requestDBId = requestDAO.getLastId();
        request.setId(requestDBId);
        return request;
    }

    @Override
    public void updateToExecuted(int requestId) {
        if(requestDAO.isExists(requestId))
            requestDAO.updateToExecuted(requestId);
    }

    @Override
    public void updateToSent(int requestId) {
        if(requestDAO.isExists(requestId))
            requestDAO.updateToSent(requestId);
    }
}
