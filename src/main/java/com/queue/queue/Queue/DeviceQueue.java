package com.queue.queue.Queue;

import com.queue.queue.LinkedQueuesRealisation.LinkedQueue;
import com.queue.queue.Request;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component("DeviceQueue")
public class DeviceQueue implements IQueue {
    private LinkedQueue queue = new LinkedQueue();
    private Lock lock = new ReentrantLock();
    private static final int MINToMILLISECOND = 60000;

    public void setQueue(LinkedQueue queue){
        this.queue = queue;
    }

    public void setRequest (Request request) {
        lock.lock();
        try {
            if (request.getDeleteTime() != 0) {
                    queue.setRequest(request);
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            //TODO add DeleteFromBd
                            deleteRequest(request);
                        }
                    };
                    timer.schedule(task,MINToMILLISECOND * request.getDeleteTime());
            } else {
                queue.setRequest(request);
            }
        }
        finally {
            lock.unlock();
        }
    }

    public Request getRequest(){
        lock.lock();
        try {
            return queue.getRequest();
        }
        finally {
            lock.unlock();
        }
    }

    public boolean deleteRequest(Request request){
        lock.lock();
        try {
            return queue.deleteFromQueue(request);
        }
        finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

