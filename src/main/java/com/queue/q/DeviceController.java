package com.queue.q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping(value = "/device")
public class DeviceController {

        @Autowired
        private queueRepository IQreposytory;

        @GetMapping
        public ResponseEntity<Request> checkDeviceRequest(){
        if(IQreposytory.DeviceQIsEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(IQreposytory.getDevicePoll(),HttpStatus.CREATED);
        }
    }

}
