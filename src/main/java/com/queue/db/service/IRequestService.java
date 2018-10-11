package com.queue.db.service;

import com.queue.q.Request;

import java.util.List;

public interface IRequestService {

    List<Request> getAll();

    List<Request> getAllUnexecuted();

    int addRequest(Request request);

    void updateToExecuted(int requestId);
}
