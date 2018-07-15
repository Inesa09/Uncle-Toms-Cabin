package com.queue.q.Controllers;

import com.queue.q.Queue.QueueRepository;
import com.queue.q.Request;
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
        private QueueRepository IQrepository;

        @GetMapping
        public ResponseEntity<Request> checkDeviceRequest(){
        if(IQrepository.deviceQIsEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(IQrepository.getDevicePoll(),HttpStatus.OK);
        }
    }

}
