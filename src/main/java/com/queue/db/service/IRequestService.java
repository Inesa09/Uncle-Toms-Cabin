package com.queue.db.service;

import com.queue.db.entity.RequestDB;
import com.queue.q.Request;

import java.util.List;

public interface IRequestService {

    List<RequestDB> getAll();

    void fillQueuesWithUnexecutedRequests();

    void fillQueueWithUnsentRequests();

    Request saveAndSetId(Request request);

    void updateToExecuted(int requestId);

    void updateToSent(int requestId);
}
