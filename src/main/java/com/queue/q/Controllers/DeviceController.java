package com.queue.q.Controllers;

import com.queue.q.Queue.DeviceQueue;
import com.queue.q.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private DeviceQueue deviceQueue;

    @GetMapping
    public ResponseEntity<Request> checkDeviceRequest(){
        if(deviceQueue.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(deviceQueue.getRequest(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Request> postDeviceRequest(@RequestBody Request request){
        if(request.isLocked()){
            Lock lock = new ReentrantLock();
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    deviceQueue.lock(lock);
                   try{
                       request.setPriority((byte)15);
                       deviceQueue.setRequest(request);
                   }
                   finally {
                       deviceQueue.unlock(lock);
                   }
                }
            };
            timer.schedule(task,1000*60*request.getTimelocked());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            deviceQueue.setRequest(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}