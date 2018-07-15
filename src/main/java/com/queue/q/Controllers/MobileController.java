package com.queue.q.Controllers;

import com.queue.q.Queue.QueueRepository;
import com.queue.q.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    private QueueRepository IQrepository;

    @GetMapping
    public ResponseEntity<Object> get (){
        if(IQrepository.mobileQIsEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(IQrepository.getMobilePoll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity post (@RequestBody Request request) {
        IQrepository.setRequest(request.getServiceId(), request);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
