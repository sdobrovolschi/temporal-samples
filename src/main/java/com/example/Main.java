package com.example;

import io.temporal.spring.boot.TemporalOptionsCustomizer;
import io.temporal.worker.WorkerOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
@EnableFeignClients
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    TemporalOptionsCustomizer<WorkerOptions.Builder> workerOptionsCustomizer() {
        return options -> options
                // capacity
                .setMaxConcurrentWorkflowTaskPollers(1)
                .setMaxConcurrentActivityTaskPollers(1)
                .setMaxConcurrentWorkflowTaskExecutionSize(4)
                .setMaxConcurrentActivityExecutionSize(8)
                // sticky task queue
                .setStickyQueueScheduleToStartTimeout(Duration.ofSeconds(5)) // https://docs.temporal.io/sticky-execution
                // rate limiting
                .setMaxWorkerActivitiesPerSecond(200)
                .setMaxTaskQueueActivitiesPerSecond(800).setDefaultDeadlockDetectionTimeout(7000);
//                .setUsingVirtualThreads(true);
    }
}
