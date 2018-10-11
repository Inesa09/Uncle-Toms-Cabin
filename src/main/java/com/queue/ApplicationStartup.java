package com.queue;

import com.queue.db.service.IRequestService;
import com.queue.q.Constant.ServiceID;
import com.queue.q.Queue.DeviceQueue;
import com.queue.q.Queue.IQueue;
import com.queue.q.Queue.VideoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<IQueue> queues = requestService.getQueuesWithUnexecutedRequests();
        deviceQueue = (DeviceQueue) queues.get(ServiceID.DEVICE_ID - 1);
        videoQueue = (VideoQueue) queues.get(ServiceID.VIDEO_ID - 1);
    }
}
