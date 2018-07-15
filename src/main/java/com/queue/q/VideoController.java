package com.queue.q;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private queueRepository IQrepository;

    @GetMapping
    public ResponseEntity<Object> get (){
        if(IQrepository.VideoQIsEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(IQrepository.getVideoPoll(), HttpStatus.OK);
    }
}