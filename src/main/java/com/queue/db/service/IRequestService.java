package com.queue.db.service;

import com.queue.q.LinkedQueuesRealisation.LinkedQueue;
import com.queue.q.Request;

import java.util.List;

public interface IRequestService {

    List<Request> getAll();

    LinkedQueue[] getQueuesWithUnexecutedRequests();

    Request saveAndSetId(Request request);

    void updateToExecuted(int requestId);
}
