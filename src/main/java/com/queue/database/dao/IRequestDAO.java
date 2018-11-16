package com.queue.database.dao;

import com.queue.database.entity.RequestEntity;
import com.queue.queue.Request;

import java.util.List;

public interface IRequestDAO {

    List<RequestEntity> getAllWithTheSameStatus(byte statusId);

    void add(Request request);

    void updateStatus(String guid, byte statusId);

    void updateCompletionTime(String guid);

    boolean isExists(String guid);
}

