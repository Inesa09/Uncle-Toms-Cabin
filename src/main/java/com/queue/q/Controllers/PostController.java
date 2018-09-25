package com.queue.q.Controllers;

import com.queue.q.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/post")
public class PostController {

    @PostMapping
    public ResponseEntity<Object> postToMobileAndWeb(@RequestBody Request requestToPost){
        String mobileUrl = "http://localhost:8080/mobile";
        String webUrl = "http://localhost:8080/web";
        int response1, response2;

        response1 = postRequest(requestToPost, mobileUrl).getStatusCodeValue();
        response2 = postRequest(requestToPost, webUrl).getStatusCodeValue();

        if(response1<300 && response2<300)
            return new ResponseEntity<>(HttpStatus.OK);
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