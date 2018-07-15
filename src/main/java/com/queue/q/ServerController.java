package com.queue.q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/server")
public class ServerController {

    @Autowired
    private queueRepository IQrepository;

    @GetMapping
    public ResponseEntity<Request> checkServerRequest() {
        if (IQrepository.serverQIsEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(IQrepository.getServerPoll(), HttpStatus.CREATED);
        }
    }
    @PostMapping
    public void getRequestFromServer(ResponseEntity<Request> request){
        if(request.getBody().getServiceId()>=0 && request.getBody().getServiceId()<=3){
            IQrepository.setRequest(request.getBody().getServiceId(),request.getBody());
        }
    }
}
