package com.vuntt1412.epldashboard.conf;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.vuntt1412.epldashboard.event.MyEvent;

@Component
public class Foo implements ApplicationContextAware, BeanNameAware, InitializingBean, DisposableBean {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent() {
        System.out.println("Publishing myEvent.");
        MyEvent myEvent = new MyEvent(this);
        applicationEventPublisher.publishEvent(myEvent);
    }

    public Foo() {
        System.out.println("invoke constructor method");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("invoke setBeanName method");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("invoke setApplicationContext method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("invoke afterPropertiesSet method");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("invoke destroy method");
    }

    @PostConstruct
    public void postConstructMethod() {
        System.out.println("invoke postConstructMethod method");
    }

    @PreDestroy
    public void preDestroyMethod() {
        System.out.println("invoke preDestroyMethod method");
    }
}
