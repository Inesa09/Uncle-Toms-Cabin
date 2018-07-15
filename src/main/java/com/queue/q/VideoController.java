package com.queue.q;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.queue.q.QApplication.videoQueue;


@RestController
@RequestMapping("/video")
public class VideoController {

    @GetMapping
    public ResponseEntity<Object> get (){
        if(videoQueue.isEmpty())
            return new ResponseEntity<>(videoQueue.poll(), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(videoQueue.poll(), HttpStatus.OK);
    }
}