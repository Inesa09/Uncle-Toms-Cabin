package com.queue.q;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    private queueRepository IQrepository;

    @GetMapping
    public ResponseEntity<Object> get (){
        if(IQrepository.MobileQIsEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(IQrepository.getMobilePoll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity post (@RequestBody Request request) {
        IQrepository.setRequest(request.getServiceId(), request);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
