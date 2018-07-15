package com.queue.q;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

import static com.queue.q.QApplication.*;

@RestController
@RequestMapping("/mobile")
public class MobileController {

    private Queue queues[] = {mobileQueue, deviceQueue, videoQueue, serverQueue};

    @GetMapping
    public ResponseEntity<Object> get (){
        if(mobileQueue.isEmpty())
            return new ResponseEntity<>(mobileQueue.poll(), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(mobileQueue.poll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity post (@RequestBody Request request) {
        queues[request.getServiceId()].add(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
