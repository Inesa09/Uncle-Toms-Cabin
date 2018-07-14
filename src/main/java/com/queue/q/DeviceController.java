package com.queue.q;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping(value = "/device")
public class DeviceController {
        @GetMapping
        public ResponseEntity<Request> checkDeviceRequest(){
        if(QApplication.devicequeue.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(QApplication.devicequeue.poll(),HttpStatus.CREATED);
        }
    }

}
