package com.daemongear.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * ThreadConfig
 */
@Configuration
public class TaskPoolConfiguration implements AsyncConfigurer {

    private static final int MAX_POOL_SIZE = 10;
    private static final int CORE_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setThreadNamePrefix("dmusic-thread-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}