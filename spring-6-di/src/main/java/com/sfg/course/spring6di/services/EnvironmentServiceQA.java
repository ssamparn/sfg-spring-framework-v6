package com.sfg.course.spring6di.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service
public class EnvironmentServiceQA implements EnvironmentService {

    @Override
    public String getEnv() {
        return "qa";
    }
}
