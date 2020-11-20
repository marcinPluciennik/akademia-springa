package com.springcourse.kursspringboot2aop;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

@Component
public class Hello {

    @EventListener(ApplicationReadyEvent.class)
    @HelloAcpect
    @Schedules({
        @Scheduled(fixedDelay = 1000),
            @Scheduled(cron = "0 0 12 * * ?")

    })

    public String sayHello() throws InterruptedException{
        Thread.sleep(3000);
        System.out.println("Hello");
        return "Hello!";
    }
}
