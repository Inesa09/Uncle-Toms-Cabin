package com.queue.db.service;

import com.queue.q.Queue.IQueue;
import com.queue.q.Request;

import java.util.List;

public interface IRequestService {

    List<Request> getAll();

    List<IQueue> getQueuesWithUnexecutedRequests();

    Request saveAndSetId(Request request);

    void updateToExecuted(int requestId);
}
