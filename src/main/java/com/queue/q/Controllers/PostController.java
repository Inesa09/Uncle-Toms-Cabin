package com.queue.q.Controllers;

import com.queue.db.service.IRequestService;
import com.queue.q.Queue.IPostQueue;
import com.queue.q.Queue.PostRequestQueue;
import com.queue.q.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/post")
public class PostController {
     private static final String MOBILE_URL = "http://localhost:8080/mobile";
     private static final String WEB_URL = "http://localhost:8080/web";

    @Autowired
    private PostRequestQueue mobileQueue;
    @Autowired
    private PostRequestQueue webQueue;

    @Autowired
    IRequestService requestService;

    @PostMapping
    public void postToMobileAndWeb(@RequestBody Request requestToPost){
        requestService.updateToExecuted(requestToPost.getId());

        mobileQueue.setRequest(requestToPost);
        webQueue.setRequest(requestToPost);

        Thread ThreadToWeb = new Thread(() -> sendAllRequest(webQueue, WEB_URL));
        ThreadToWeb.start();

        sendAllRequest(mobileQueue, MOBILE_URL);
    }

    private ResponseEntity<Request>  postRequest(Request requestToPost, String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Request> request = new HttpEntity<>(requestToPost);
        ResponseEntity<Request> response = restTemplate
                .exchange(url, HttpMethod.POST, request, Request.class);
        return response;
    }

    private void sendAllRequest(IPostQueue queue, String URL){
        while(!queue.isEmpty()){
            ResponseEntity<Request> response = postRequest(queue.peekRequest(), URL);
            if((response.getStatusCode() == HttpStatus.OK)){
               Request request = queue.getRequest();
               requestService.updateToSent(request.getId());
            } else {
                return;
            }
        }
    }
}


