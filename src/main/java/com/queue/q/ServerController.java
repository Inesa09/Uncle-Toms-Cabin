package com.queue.q;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(IQrepository.getServerPoll(), HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<Request> getRequestFromServer(@RequestBody  Request request){
        if(request.getServiceId()>=0 && request.getServiceId()<=3){
            IQrepository.setRequest(request.getServiceId(),request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
