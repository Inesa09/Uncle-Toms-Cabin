package com.queue.queue.Controllers;

import com.queue.queue.Queue.VideoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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