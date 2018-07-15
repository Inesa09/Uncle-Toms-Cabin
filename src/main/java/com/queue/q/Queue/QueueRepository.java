package com.queue.q.Queue;
import com.queue.q.Request;
import org.springframework.stereotype.Component;
import java.util.PriorityQueue;
import java.util.Queue;
@Component("QueueRepository")
public class QueueRepository {
   private Queue<Request> mobileQueue= new PriorityQueue<>(new Comparator());
   private Queue<Request> deviceQueue = new PriorityQueue<>(new Comparator());
   private Queue<Request> serverQueue = new PriorityQueue<>(new Comparator());
   private Queue<Request> videoQueue = new PriorityQueue<>(new Comparator());
   private Queue queues[] = {mobileQueue, deviceQueue, videoQueue, serverQueue};

    public Request getMobilePoll(){
        return mobileQueue.poll();
    }public Request getDevicePoll(){
        return deviceQueue.poll();
    }public Request getServerPoll(){
        return serverQueue.poll();
    }public Request getVideoPoll(){
        return videoQueue.poll();
    }
    public void setRequest(byte id, Request request){
        queues[id].add(request);
    }

    public boolean mobileQIsEmpty(){
        if(mobileQueue.isEmpty()){
            return true;
        }
        return false;
    }public boolean deviceQIsEmpty(){
        if(deviceQueue.isEmpty()){
            return true;
        }
        return false;
    }public boolean serverQIsEmpty(){
        if(serverQueue.isEmpty()){
            return true;
        }
        return false;
    }public boolean videoQIsEmpty(){
        if(videoQueue.isEmpty()){
            return true;
        }
        return false;
    }

}