package com.queue.q;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.PriorityQueue;
import java.util.Queue;
@RestController
public class DeviceController {
    @RequestMapping(value = "/device", method = RequestMethod.GET)
        public Request checkDeviceRequest(){
        if(QApplication.devicequeue.isEmpty()){
            return null;
        }
        else {
            return QApplication.devicequeue.poll();
        }
    }

}
