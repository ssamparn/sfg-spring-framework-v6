package com.sfg.course.spring6di.controllers;

import com.sfg.course.spring6di.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyInjectedController {

    @Qualifier("propertyGreetingService")
    @Autowired
    private GreetingService greetingService;

    public String sayHello(){
        return greetingService.sayGreeting();
    }

}
