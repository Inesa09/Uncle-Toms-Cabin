package com.queue.q;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.PriorityQueue;
import java.util.Queue;

@SpringBootApplication
public class QApplication {
    static Queue<Request> mobileQueue = new PriorityQueue<>(new com.queue.q.Comparator());
    static Queue<Request> deviceQueue = new PriorityQueue<>(new com.queue.q.Comparator());
    static Queue<Request> serverQueue = new PriorityQueue<>(new com.queue.q.Comparator());
    static Queue<Request> videoQueue = new PriorityQueue<>(new com.queue.q.Comparator());

    public static void main(String[] args) {
        SpringApplication.run(QApplication.class, args);
    }


}