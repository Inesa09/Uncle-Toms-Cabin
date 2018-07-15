package com.queue.q;
import org.springframework.stereotype.Component;
import java.util.PriorityQueue;
import java.util.Queue;
@Component("queueRepository")
public class queueRepository {
   private Queue<Request> mobilequeue= new PriorityQueue<>(new Comparator());
   private Queue<Request> devicequeue = new PriorityQueue<>(new Comparator());
   private Queue<Request> serverqueue = new PriorityQueue<>(new Comparator());
   private Queue<Request> videoqueue = new PriorityQueue<>(new Comparator());
   private Queue queues[] = {mobilequeue,devicequeue,serverqueue,videoqueue};

    public Request getMobilePoll(){
        return mobilequeue.poll();
    }public Request getDevicePoll(){
        return devicequeue.poll();
    }public Request getServerPoll(){
        return serverqueue.poll();
    }public Request getVideoPoll(){
        return videoqueue.poll();
    }
    public void setRequest(byte id, Request request){
        queues[id].add(request);
    }

    public boolean MobileQIsEmpty(){
        if(mobilequeue.isEmpty()){
            return true;
        }
        return false;
    }public boolean DeviceQIsEmpty(){
        if(devicequeue.isEmpty()){
            return true;
        }
        return false;
    }public boolean ServerQIsEmpty(){
        if(serverqueue.isEmpty()){
            return true;
        }
        return false;
    }public boolean VideoQIsEmpty(){
        if(videoqueue.isEmpty()){
            return true;
        }
        return false;
    }

}