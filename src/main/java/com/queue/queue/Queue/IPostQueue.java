package com.queue.queue.Queue;

import com.queue.queue.Request;

public interface IPostQueue extends IQueue {
     Request peekRequest();
}
