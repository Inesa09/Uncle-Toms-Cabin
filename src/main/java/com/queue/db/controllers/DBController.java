package com.queue.db.controllers;

import com.queue.db.service.IRequestService;
import com.queue.q.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("requests")
public class DBController {

    @Autowired
    IRequestService requestService;

    @GetMapping("all")
    public ResponseEntity<List<Request>> getAll(){
        List<Request> list = requestService.getAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("allUnexecuted")
    public ResponseEntity<List<Request>> getAllUnexecuted(){
        List<Request> list = requestService.getAllUnexecuted();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Integer> add(@RequestBody Request request){
        int maxId = requestService.addRequest(request);
        return new ResponseEntity<>(maxId, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody int id){
        requestService.updateToExecuted(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}