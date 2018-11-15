package com.queue.queue;

import com.queue.queue.Queue.*;
import com.queue.constants.*;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component("QueueRepository")
public class QueueRepository {
    private IQueue deviseQueue = new DeviceQueue();
    private IQueue videoQueue = new VideoQueue();
    private IPostQueue mobileQueue = new PostRequestQueue();
    private IPostQueue webQueue = new PostRequestQueue();

    public IQueue getQueueByServiceID(int serviceId){
        switch (serviceId){
            case (ServiceID.DEVICE_ID):
                return deviseQueue;
            case (ServiceID.VIDEO_ID):
                return videoQueue;
            case (ServiceID.MOBILE_ID):
                return mobileQueue;
            case (ServiceID.WEB_ID):
                return webQueue;
            default:
                return null;
        }
    }

}
