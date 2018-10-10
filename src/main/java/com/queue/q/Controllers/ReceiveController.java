package com.queue.q.Controllers;

import com.queue.q.Constant.ServiceID;
import com.queue.q.Constant.PriorityType;
import com.queue.q.Queue.DeviceQueue;
import com.queue.q.Queue.IQueue;
import com.queue.q.Queue.VideoQueue;
import com.queue.q.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping(value = "/queue")
public class ReceiveController {
    private static final int MINToMILLISECOND = 60000;

    @Autowired
    DeviceQueue deviceQueue;
    @Autowired
    VideoQueue videoQueue;

    @PostMapping
    public ResponseEntity<Request> addRequestInQueue(@RequestBody Request request){
        switch (request.getServiceId()){
            case (ServiceID.DEVICE_ID):
                return postRequestInQueue(deviceQueue, request);

            case (ServiceID.VIDEO_ID):
                return postRequestInQueue(videoQueue, request);

            default:
                return new ResponseEntity <>(HttpStatus.BAD_REQUEST);
        }
    }


    private ResponseEntity<Request> postRequestInQueue(IQueue queue , Request request){
        if(request.getTimelock() != 0){
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    request.setPriority(PriorityType.HIGH_PRIORITY);
                    queue.setRequest(request);
                }
            };
            timer.schedule(task,MINToMILLISECOND*request.getTimelock());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            queue.setRequest(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
