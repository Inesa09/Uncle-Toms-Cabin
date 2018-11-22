package com.queue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.queue.nosqlDB", "com.queue.queue"})
public class QApplication {
    public static void main(String[] args) {
        SpringApplication.run(QApplication.class, args);
    }
}