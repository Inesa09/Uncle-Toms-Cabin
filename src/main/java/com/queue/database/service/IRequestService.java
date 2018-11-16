package com.queue.database.service;

import com.queue.queue.Request;

public interface IRequestService {

    void fillQueuesWithUnexecutedRequests();

    void fillQueueWithUnsentRequests();

    Request save(Request request);

    void updateToExecuted(String guid);

    void updateToSent(String guid);
}
