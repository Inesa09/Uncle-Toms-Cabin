package com.queue;

import com.queue.nosqlDB.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

//@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    IRequestService requestService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        fillQueues();
    }

    private void fillQueues(){
        System.out.println("4helooooo");
        requestService.fillQueuesWithUnexecutedRequests();
    }
}
