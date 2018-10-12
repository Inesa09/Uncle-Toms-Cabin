package com.queue;

import com.queue.db.service.IRequestService;
import com.queue.q.Constant.ServiceID;
import com.queue.q.LinkedQueuesRealisation.LinkedQueue;
import com.queue.q.Queue.DeviceQueue;
import com.queue.q.Queue.VideoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    DeviceQueue deviceQueue;
    @Autowired
    VideoQueue videoQueue;

    @Autowired
    IRequestService requestService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        fillQueues();
    }

    private void fillQueues(){
        LinkedQueue[] queues = requestService.getQueuesWithUnexecutedRequests();
        deviceQueue.setQueue(queues[ServiceID.DEVICE_ID - 1]);
        videoQueue.setQueue(queues[ServiceID.VIDEO_ID - 1]);
    }
}
