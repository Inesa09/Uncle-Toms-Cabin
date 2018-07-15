package com.queue.q;
import org.springframework.stereotype.Component;
import java.util.PriorityQueue;
import java.util.Queue;
@Component("queueRepository")
public class queueRepository {
   private Queue<Request> Mobilequeue= new PriorityQueue<>(new Comparator());
   private Queue<Request> Devicequeue = new PriorityQueue<>(new Comparator());
   private Queue<Request> Serverqueue = new PriorityQueue<>(new Comparator());
   private Queue<Request> Videoqueue = new PriorityQueue<>(new Comparator());
   private Queue Queues[] = {Mobilequeue,Devicequeue,Serverqueue,Videoqueue};

    public Request getMobilePoll(){
        return Mobilequeue.poll();
    }public Request getDevicePoll(){
        return Devicequeue.poll();
    }public Request getServerPoll(){
        return Serverqueue.poll();
    }public Request getVideoPoll(){
        return Videoqueue.poll();
    }
    public void setRequest(byte id, Request request){
        Queues[id].add(request);
    }

    public boolean mobileQIsEmpty(){
        if(Mobilequeue.isEmpty()){
            return true;
        }
        return false;
    }public boolean deviceQIsEmpty(){
        if(Devicequeue.isEmpty()){
            return true;
        }
        return false;
    }public boolean serverQIsEmpty(){
        if(Serverqueue.isEmpty()){
            return true;
        }
        return false;
    }public boolean videoQIsEmpty(){
        if(Videoqueue.isEmpty()){
            return true;
        }
        return false;
    }

}