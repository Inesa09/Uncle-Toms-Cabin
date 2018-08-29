package com.queue.q.Controllers;

import com.queue.q.Queue.VideoQueue;
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
    private VideoQueue videoQueue;

    @GetMapping
    public ResponseEntity<Object> checkVideoRequest(){
        if(videoQueue.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(videoQueue.getRequest(), HttpStatus.OK);
    }
}