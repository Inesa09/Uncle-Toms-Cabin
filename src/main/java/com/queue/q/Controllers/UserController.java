package com.queue.q.Controllers;

import com.queue.q.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @PostMapping
    public ResponseEntity<Request> sendRequestToUserService(@RequestBody Request requestToUserService){
        String userUrl = "http://localhost:8080/userservice";

        ResponseEntity<Request> responceUser = postRequest(requestToUserService, userUrl);

        if(responceUser.getStatusCode() == HttpStatus.OK){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Request>  postRequest(Request requestToPost, String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Request> request = new HttpEntity<>(requestToPost);
        ResponseEntity<Request> response = restTemplate
                .exchange(url, HttpMethod.POST, request, Request.class);
        return response;
    }
}

