package com.queue.queue.Controllers;

import com.queue.queue.Queue.DeviceQueue;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}


