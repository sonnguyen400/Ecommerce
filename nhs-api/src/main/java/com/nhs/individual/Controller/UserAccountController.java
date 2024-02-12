package com.nhs.individual.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {


    @RequestMapping(value = "/test")
    public String test(){
        return "test";
    }
}
