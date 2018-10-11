package com.queue.db.dao;

import com.queue.db.entity.RequestDB;
import com.queue.q.Request;

import java.util.List;


public interface IRequestDAO {

    List<Request> getAll();

    List<Request> getAllUnexecuted();

    int getLastId();

    void add(RequestDB request);

    void updateToExecuted(int requestId);

    boolean isExists(int requestId);
}

