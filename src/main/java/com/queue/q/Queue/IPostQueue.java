package com.queue.q.Queue;

import com.queue.q.Request;

public interface IPostQueue extends IQueue {
     Request peekRequest();
}
