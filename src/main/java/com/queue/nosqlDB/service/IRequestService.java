package com.queue.nosqlDB.service;

import com.queue.queue.Request;

public interface IRequestService {

    String getCreationTime(String guid);

    void fillQueuesWithUnexecutedRequests();

    void save(Request request);

    void updateToExecuted(String guid);

    void updateToSent(String guid);

    void delete(String guid);
}
