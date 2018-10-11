package com.queue.q.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.queue.q.Queue.DeviceQueue;
import com.queue.q.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


