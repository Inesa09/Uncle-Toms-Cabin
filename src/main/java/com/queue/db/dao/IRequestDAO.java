package com.queue.db.dao;

import com.queue.db.entity.RequestDB;

import java.util.List;


public interface IRequestDAO {

    List<RequestDB> getAll();

    List<RequestDB> getAllUnexecuted();

    List<RequestDB> getAllUnsent();

    int getLastId();

    void add(RequestDB request);

    void updateToExecuted(int requestId);

    void updateToSent(int requestId);

    boolean isExists(int requestId);
}

