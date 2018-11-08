package com.queue.database.service;

import com.queue.database.entity.RequestDB;
import com.queue.queue.Request;

import java.util.List;

public interface IRequestService {

    List<RequestDB> getAll();

    void fillQueuesWithUnexecutedRequests();

    void fillQueueWithUnsentRequests();

    Request saveAndSetId(Request request);

    void updateToExecuted(int requestId);

    void updateToSent(int requestId);
}
