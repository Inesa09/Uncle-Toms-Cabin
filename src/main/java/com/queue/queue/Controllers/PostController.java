package com.queue.queue.Controllers;

import com.queue.constants.ServiceID;
import com.queue.database.service.IRequestService;
import com.queue.queue.Queue.IPostQueue;
import com.queue.queue.QueueRepository;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.queue.constants.URL.MOBILE_URL;
import static com.queue.constants.URL.WEB_URL;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private QueueRepository repository;

    @Autowired
    IRequestService requestService;

    @PostMapping
    public void postToMobileAndWeb(@RequestBody Request requestToPost){
        requestService.updateToExecuted(requestToPost.getGuid());

        repository.getQueueByServiceID(ServiceID.MOBILE_ID).setRequest(requestToPost);
        repository.getQueueByServiceID(ServiceID.WEB_ID).setRequest(requestToPost);

        Thread ThreadToWeb = new Thread(() -> sendAllRequest((IPostQueue)repository.getQueueByServiceID(ServiceID.WEB_ID), WEB_URL));
        ThreadToWeb.start();

        sendAllRequest((IPostQueue)repository.getQueueByServiceID(ServiceID.MOBILE_ID), MOBILE_URL);
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
               requestService.updateToSent(request.getGuid());
            } else {
                return;
            }
        }
    }
}


