package com.queue.q.Controllers;

import com.queue.q.Queue.DeviceQueue;
import com.queue.q.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private DeviceQueue deviceQueue;

    @GetMapping
    public ResponseEntity<Request> checkDeviceRequest(){
        String s = "MaxLoh";
        Request request = new Request((byte)1, (byte)1, s);
        deviceQueue.setRequest(request);
        if(deviceQueue.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(deviceQueue.getRequest(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Request> postDeviceRequest(@RequestBody Request request){
        if(request.getTimelock() != 0){
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    request.setPriority((byte)15);
                    deviceQueue.setRequest(request);
                }
            };
            timer.schedule(task,1000*60*request.getTimelock());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            deviceQueue.setRequest(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}


