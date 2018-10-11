package com.queue.db.service;

import com.queue.db.dao.IRequestDAO;
import com.queue.db.entity.RequestDB;
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
    public List<Request> getAllUnexecuted() {
        return requestDAO.getAllUnexecuted();
    }

    @Override
    public synchronized int addRequest(Request request) {
        RequestDB newRequest = new RequestDB(request);
        requestDAO.add(newRequest);
        return requestDAO.getLastId();
    }

    @Override
    public void updateToExecuted(int requestId) {
        requestDAO.updateToExecuted(requestId);
    }
}
