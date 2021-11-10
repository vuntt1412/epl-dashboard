package com.vuntt1412.epldashboard.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener implements ApplicationListener {
    // catch all Spring-based events and print them
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}
