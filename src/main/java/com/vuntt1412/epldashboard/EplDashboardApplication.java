package com.vuntt1412.epldashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.vuntt1412.epldashboard.conf.Foo;

@SpringBootApplication
public class EplDashboardApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(EplDashboardApplication.class, args);
        Foo foo = ctx.getBean(Foo.class);
        foo.publishCustomEvent();
        ctx.close();
    }

}
