package com.queue.q;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DeviceController {
    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public Request checkDeviceRequest(){
        if(QApplication.deviceQueue.isEmpty()){
            return null;
        }
        else {
            return QApplication.deviceQueue.poll();
        }
    }

}

