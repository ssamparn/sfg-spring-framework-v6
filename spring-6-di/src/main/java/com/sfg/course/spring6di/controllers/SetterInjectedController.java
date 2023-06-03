package com.sfg.course.spring6di.controllers;

import com.sfg.course.spring6di.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class SetterInjectedController {

    @Autowired
    private GreetingService greetingService;

    @Qualifier("setterGreetingBean")
    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        System.out.println("SetterInjectedController.setGreetingService");
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
